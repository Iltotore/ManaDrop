package fr.il_totore.manadrop.mcp.task;

import fr.il_totore.manadrop.mcp.MCPExtension;
import org.gradle.api.tasks.Copy;

import javax.inject.Inject;

public class CopyMinecraftSources extends Copy {

    private MCPExtension extension;

    @Inject
    public CopyMinecraftSources(MCPExtension extension) {
        this.extension = extension;
        update();
    }

    private void update() {
        into(extension.getClientModule());
    }
}
