package fr.il_totore.manadrop.mcp.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class DownloadMCP extends DefaultTask {

    private String urlPrefix = "http://www.modcoderpack.com/files/mcp";
    private int versionId;
    private File location;
    private boolean refresh = false;

    @Inject
    public DownloadMCP(Project project) {
        this.location = new File(project.getProjectDir(), "/downloads/mcp.zip");
    }

    @TaskAction
    public void run() throws IOException {
        Objects.requireNonNull(location, "location cannot be null !");
        HttpURLConnection connection = (HttpURLConnection) new URL(urlPrefix + versionId + ".zip").openConnection();
        connection.setDoInput(true);
        if(!location.getParentFile().exists()) location.getParentFile().mkdirs();
        if(refresh && location.exists()) location.delete();
        Files.copy(connection.getInputStream(), location.toPath());
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public File getLocation() {
        return location;
    }

    public void setLocation(File location) {
        this.location = location;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
