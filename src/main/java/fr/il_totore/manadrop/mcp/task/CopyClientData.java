package fr.il_totore.manadrop.mcp.task;

import fr.il_totore.manadrop.util.MinecraftOS;
import org.gradle.api.tasks.Copy;

import javax.inject.Inject;
import java.io.File;

public class CopyClientData extends Copy {

    @Inject
    public CopyClientData(MinecraftOS os, File jarsFolder) {
        from(os.getMinecraftHome());
        into(jarsFolder);
    }
}
