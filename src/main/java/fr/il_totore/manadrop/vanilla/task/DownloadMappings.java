package fr.il_totore.manadrop.vanilla.task;

import fr.il_totore.manadrop.vanilla.VanillaExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class DownloadMappings extends DefaultTask {

    private boolean refresh = false;
    private VanillaExtension extension;
    private File downloadDir;

    @Inject
    public DownloadMappings(VanillaExtension extension, File downloadDir) {
        this.extension = extension;
        this.downloadDir = downloadDir;
    }

    @TaskAction
    public void run() throws IOException {
        download(extension.getClient().getMappingUrl(), downloadDir, "client.json");
        download(extension.getServer().getMappingUrl(), downloadDir, "server.json");
    }

    private void download(String url, File dir, String child) throws IOException {
        System.out.println("Downloading " + child + "...");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoInput(true);
        if(!dir.exists()) dir.mkdirs();
        File location = new File(dir, child);
        if(refresh && location.exists()) location.delete();
        Files.copy(connection.getInputStream(), location.toPath());
    }

    public File getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(File downloadDir) {
        this.downloadDir = downloadDir;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
