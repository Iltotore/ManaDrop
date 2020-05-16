package fr.il_totore.manadrop;

import org.gradle.api.Project;

public interface ProjectExtension {

    Project getProject();

    void setProject(Project project);
}
