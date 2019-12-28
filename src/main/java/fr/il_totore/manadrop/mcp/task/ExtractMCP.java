package fr.il_totore.manadrop.mcp.task;

import fr.il_totore.manadrop.task.ExtractFile;

import javax.inject.Inject;
import java.io.File;

public class ExtractMCP extends ExtractFile {

    @Inject
    public ExtractMCP(File downloadDir, File projectDir) {
        super(new File(downloadDir, "mcp.zip"), projectDir);
    }
}
