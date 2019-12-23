package fr.il_totore.manadrop;

import com.amihaiemil.eoyaml.Yaml;
import fr.il_totore.manadrop.optional.OptionalArrayList;
import fr.il_totore.manadrop.optional.OptionalList;
import fr.il_totore.manadrop.yaml.EditableYamlMappingBuilder;
import fr.il_totore.manadrop.yaml.YamlSerializable;
import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.util.ConfigureUtil;

import java.util.Optional;

public class Permission implements YamlSerializable {

    private String name;
    private Optional<String> description = Optional.empty();
    private Optional<DefaultType> defaultType = Optional.empty();
    private OptionalList<ChildPermission> children = new OptionalArrayList<>();

    @Override
    public void write(EditableYamlMappingBuilder permissionBuilder) {
        if(name == null) throw new NullPointerException("name cannot be null !");
        EditableYamlMappingBuilder builder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
        description.ifPresent(value -> builder.add("description", value));
        defaultType.ifPresent(value -> builder.add("default", value.getId()));
        children.ifAllPresent(0, children -> {
            EditableYamlMappingBuilder childBuilder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
            children.forEach(child -> childBuilder.add(child.getName(), String.valueOf(child.doesInherit())));
            builder.add("children", childBuilder.build());
        });
        permissionBuilder.add(name, builder.build());
    }

    public void named(String name) {
        this.name = name;
    }

    public void description(String description) {
        this.description = Optional.ofNullable(description);
    }

    public void defaultType(DefaultType defaultType) {
        this.defaultType = Optional.ofNullable(defaultType);
    }

    public void defaultType(String defaultType) {
        defaultType(DefaultType.valueOf(defaultType.toUpperCase()));
    }

    public void child(Closure<ChildPermission> c) {
        ChildPermission child = new ChildPermission();
        ConfigureUtil.configure(c, child);
        this.children.add(child);
    }

    public void child(Action<ChildPermission> action) {
        ChildPermission child = new ChildPermission();
        action.execute(child);
        this.children.add(child);
    }

    public enum DefaultType {
        GRANT_PLAYER("true"),
        DENY_PLAYER("false"),
        GRANT_OP("op"),
        DENY_OP("not op");

        private String id;

        DefaultType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static class ChildPermission {

        private String name;
        private boolean inherit = true;

        public void named(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void inherit(boolean inherit) {
            this.inherit = inherit;
        }

        public boolean doesInherit() {
            return inherit;
        }
    }
}
