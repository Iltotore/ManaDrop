package fr.il_totore.manadrop.spigot;

import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.Optional;

public class SpigotExtension {

    private Project project;
    private Optional<SpigotDescription> description = Optional.empty();

    public SpigotExtension(Project project) {
        this.project = project;
    }

    public void desc(Action<SpigotDescription> action) {
        SpigotDescription description = new SpigotDescription(project);
        action.execute(description);
        this.description = Optional.of(description);
    }

    public Optional<SpigotDescription> getDescription() {
        return description;
    }
}
