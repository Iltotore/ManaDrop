package fr.il_totore.manadrop;

import fr.il_totore.manadrop.optional.OptionalArrayList;
import fr.il_totore.manadrop.optional.OptionalList;
import org.simpleyaml.configuration.ConfigurationSection;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Command implements YamlSerializable {

    private String name;
    private Optional<String> description = Optional.empty();
    private Optional<String> permission = Optional.empty();
    private OptionalList<String> aliases = new OptionalArrayList<>();
    private Optional<String> permissionMessage = Optional.empty();
    private Optional<String> usage = Optional.empty();

    public void named(String name) {
        this.name = name;
    }

    public void description(String description) {
        this.description = Optional.ofNullable(description);
    }

    public void permission(String permission) {
        this.permission = Optional.ofNullable(permission);
    }

    public void aliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public void permissionMessage(String message) {
        this.permissionMessage = Optional.ofNullable(message);
    }

    public void usage(String usage) {
        this.usage = Optional.ofNullable(usage);
    }

    @Override
    public void write(ConfigurationSection section) {
        Objects.requireNonNull(name, "name cannot be null !");
        ConfigurationSection commandSection = section.createSection(name);
        description.ifPresent(value -> commandSection.set("description", value));
        permission.ifPresent(value -> commandSection.set("permission", value));
        aliases.ifAllPresent(0, values -> commandSection.set("aliases", values));
        permissionMessage.ifPresent(value -> commandSection.set("permission-message", value));
        usage.ifPresent(value -> commandSection.set("usage", value));
    }
}
