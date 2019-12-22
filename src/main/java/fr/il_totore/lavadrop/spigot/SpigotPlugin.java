package fr.il_totore.lavadrop.spigot;

import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.Optional;

public class SpigotPlugin {

    private Project project;
    private Optional<SpigotDescription> description = Optional.empty();

    public SpigotPlugin(Project project) {
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
