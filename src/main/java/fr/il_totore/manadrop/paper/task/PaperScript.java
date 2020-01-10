package fr.il_totore.manadrop.paper.task;

import fr.il_totore.manadrop.paper.PaperExtension;
import fr.il_totore.manadrop.task.ExecuteScript;
import fr.il_totore.manadrop.util.ExtensionChild;

import javax.inject.Inject;

public class PaperScript extends ExecuteScript implements ExtensionChild<PaperExtension> {

    @Inject
    public PaperScript(PaperExtension paperExtension, String... commands) {
        super(paperExtension.getPaperDir(), commands);
    }

    @Override
    public void update(PaperExtension extension) {
        setWorkDir(extension.getPaperDir());
    }
}
