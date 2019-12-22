package fr.il_totore.lavadrop;

import com.amihaiemil.eoyaml.Yaml;
import fr.il_totore.lavadrop.optional.OptionalArrayList;
import fr.il_totore.lavadrop.optional.OptionalList;
import fr.il_totore.lavadrop.yaml.EditableYamlMappingBuilder;
import fr.il_totore.lavadrop.yaml.YamlSerializable;
import fr.il_totore.lavadrop.yaml.YamlUtil;

import java.util.Arrays;
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
    public void write(EditableYamlMappingBuilder commandsBuilder) {
        if(name == null) throw new NullPointerException("name cannot be null !");
        EditableYamlMappingBuilder builder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
        description.ifPresent(value -> builder.add("description", value));
        permission.ifPresent(value -> builder.add("permission", value));
        aliases.ifAllPresent(0, value -> builder.add("aliases", YamlUtil.fromCollection(value).build()));
        permissionMessage.ifPresent(value -> builder.add("permission-message", value));
        usage.ifPresent(value -> builder.add("usage", value));
        commandsBuilder.add(name, builder.build());
    }
}
