package fr.il_totore.lavadrop.bungeecord;

import fr.il_totore.lavadrop.Description;
import org.gradle.api.Project;

import java.io.File;

public class BungeeDescription extends Description {

    public BungeeDescription(Project project) {
        super(project, new File(project.getBuildDir(), "resources/main/bungee.yml"));
    }
}