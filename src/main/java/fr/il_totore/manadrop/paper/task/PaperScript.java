package fr.il_totore.manadrop.paper.task;

import fr.il_totore.manadrop.paper.PaperExtension;
import fr.il_totore.manadrop.task.ExecuteScript;

import javax.inject.Inject;
import java.io.IOException;

public class PaperScript extends ExecuteScript {

    private PaperExtension extension;

    @Inject
    public PaperScript(PaperExtension paperExtension, String... commands) {
        super(paperExtension.getPaperDir(), commands);
        this.extension = paperExtension;
    }

    @Override
    public void run() throws IOException {
        updateFields();
        super.run();
    }

    private void updateFields() {
        setWorkDir(extension.getPaperDir());
    }

    public PaperExtension getExtension() {
        return extension;
    }

    public void setExtension(PaperExtension extension) {
        this.extension = extension;
    }
}
