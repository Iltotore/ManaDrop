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

    @Inject
    public DownloadMappings(VanillaExtension extension) {
        this.extension = extension;
    }

    @TaskAction
    public void run() throws IOException {
        if(extension.getClient().getMappingHash() != null)
            download(extension.getClient().getMappingUrl(), extension.getEnigma().getJarsDir(), "client.txt");
        if(extension.getServer().getMappingHash() != null)
            download(extension.getServer().getMappingUrl(), extension.getEnigma().getJarsDir(), "server.txt");
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

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
