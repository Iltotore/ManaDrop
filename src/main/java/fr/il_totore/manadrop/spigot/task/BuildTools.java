package fr.il_totore.manadrop.spigot.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class BuildTools extends DefaultTask {

    private List<String> allVersions = new ArrayList<>();
    public File workDir;
    public boolean refreshBuildTools = true;
    public boolean refreshVersions = false;
    public String buildToolsURL = "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar";
    public boolean stopOnError = true;
    public boolean showMavenInstallCheckLogs = false;
    public boolean showBuildToolsLogs = true;
    public int maxRamAllowed = 1024;
    public String mavenPath = System.getenv("MAVEN_HOME") + "/bin/mvn" + (System.getenv("OS").toLowerCase().contains("win") ? ".cmd" : "");

    @TaskAction
    public void run() throws IOException, InterruptedException {
        Objects.requireNonNull(workDir, "workDir cannot be null !");
        Objects.requireNonNull(mavenPath, "Can't find mavenPath !");
        if(!workDir.exists()) workDir.mkdirs();
        File buildTools = new File(workDir, "BuildTools.jar");
        if(refreshBuildTools) buildTools.delete();
        if(!buildTools.exists()) {
            System.out.println("Downloading BuildTools...");
            HttpURLConnection connection = (HttpURLConnection) new URL(buildToolsURL).openConnection();
            connection.setDoInput(true);
            Files.copy(connection.getInputStream(), buildTools.toPath());
        }
        for(String version : allVersions) {
            try {
                System.out.println("Checking for " + version + "...");
                ProcessBuilder mavenProcessBuilder = new ProcessBuilder(mavenPath, "dependency:get", "-Dartifact=\"org.spigotmc:spigot:" + version + "-R0.1-SNAPSHOT\"");
                mavenProcessBuilder.environment().putAll(System.getenv());
                mavenProcessBuilder.inheritIO();
                Process mavenProcess = mavenProcessBuilder.start();
                if(showMavenInstallCheckLogs) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(mavenProcess.getInputStream()));
                    String line = reader.readLine();
                    while(line != null) {
                        System.out.println(line);
                        line = reader.readLine().replace("ERROR", "POSSIBLE ERROR");
                    }
                    reader.close();
                }
                mavenProcess.waitFor();
                if(mavenProcess.exitValue() == 0 && !refreshVersions) {
                    System.out.println(version + " is already installed! Skipping...");
                    continue;
                }

                System.out.println("Building " + version + "...");

                ProcessBuilder buildProcessBuilder = new ProcessBuilder("java", "-Xmx" + maxRamAllowed + "M", "-jar", "BuildTools.jar", "--rev", version);
                buildProcessBuilder.directory(workDir);

                Process buildProcess = buildProcessBuilder.start();
                if(showBuildToolsLogs) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(buildProcess.getInputStream()));
                    String line = reader.readLine();
                    while(line != null) {
                        System.out.println(line);
                        line = reader.readLine();
                    }
                    reader.close();
                }


                BufferedReader reader = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream()));
                String line = reader.readLine();
                while(line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();

                buildProcess.waitFor();
            } catch(InterruptedException e) {
                if(stopOnError) throw e;
                e.printStackTrace();
            }
        }
    }

    public void versions(String... versions) {
        allVersions.addAll(Arrays.asList(versions));
    }

    public Collection<String> allVersions() {
        return allVersions;
    }
}
