package fr.il_totore.manadrop.bungeecord;

import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.Optional;

public class BungeePlugin {

    private Project project;
    private Optional<BungeeDescription> description = Optional.empty();

    public BungeePlugin(Project project) {
        this.project = project;
    }

    public void desc(Action<BungeeDescription> action) {
        BungeeDescription description = new BungeeDescription(project);
        action.execute(description);
        this.description = Optional.of(description);
    }

    public Optional<BungeeDescription> getDescription() {
        return description;
    }
}
