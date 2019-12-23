package fr.il_totore.manadrop;

import com.amihaiemil.eoyaml.Yaml;
import fr.il_totore.manadrop.optional.OptionalArrayList;
import fr.il_totore.manadrop.optional.OptionalList;
import fr.il_totore.manadrop.task.ChildTask;
import fr.il_totore.manadrop.yaml.EditableYamlMappingBuilder;
import fr.il_totore.manadrop.yaml.YamlSerializable;
import fr.il_totore.manadrop.yaml.YamlUtil;
import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.util.ConfigureUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class Description implements YamlSerializable, ChildTask {

    private String encoding = StandardCharsets.UTF_8.name();
    private File file;

    //Required
    private String name;
    private String main;

    //Optionals
    private Optional<String> version;
    private Optional<String> apiVersion = Optional.empty();
    private OptionalList<String> authors = new OptionalArrayList<>();
    private Optional<String> description = Optional.empty();
    private Optional<String> website = Optional.empty();
    private OptionalList<String> depend = new OptionalArrayList<>();
    private OptionalList<String> softDepend = new OptionalArrayList<>();
    private OptionalList<String> loadBefore = new OptionalArrayList<>();
    private Optional<String> prefix = Optional.empty();
    private OptionalList<Command> commands = new OptionalArrayList<>();
    private OptionalList<Permission> permissions = new OptionalArrayList<>();

    public Description(Project project, File file) {
        this.file = file;
        name = project.getName();
        version = Optional.of(project.getVersion().toString());
    }

    @Override
    public void write(EditableYamlMappingBuilder builder) {
        if(name == null || name.isEmpty()) throw new NullPointerException("main cannot be null or empty !");
        builder.add("name", name);
        if(main == null) throw new NullPointerException("main cannot be null !");
        builder.add("main", main);
        version.ifPresent(value -> builder.add("version", value));
        apiVersion.ifPresent(value -> builder.add("apiVersion", value));
        authors.ifSingle(value -> builder.add("author", value));
        authors.ifSizeSuperior(1, values -> builder.add("authors", YamlUtil.fromCollection(values).build()));
        description.ifPresent(value -> builder.add("description", value));
        website.ifPresent(value -> builder.add("website", value));
        depend.ifAllPresent(0, values -> builder.add("depend", YamlUtil.fromCollection(values).build()));
        softDepend.ifAllPresent(0, values -> builder.add("softDepend", YamlUtil.fromCollection(values).build()));
        loadBefore.ifAllPresent(0, values -> builder.add("loadBefore", YamlUtil.fromCollection(values).build()));
        prefix.ifPresent(value -> builder.add("prefix", value));
        commands.ifAllPresent(0, commandList -> {
            EditableYamlMappingBuilder commandsBuilder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
            commandList.forEach(command -> command.write(commandsBuilder));
            builder.add("commands", commandsBuilder.build());
        });
        permissions.ifAllPresent(0, permList -> {
            EditableYamlMappingBuilder permBuilder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
            permList.forEach(perm -> perm.write(permBuilder));
            builder.add("permissions", permBuilder.build());
        });
    }

    @Override
    public void run(Task parent) {
        System.out.println("Building plugin.yml...");
        EditableYamlMappingBuilder builder = new EditableYamlMappingBuilder(Yaml.createYamlMappingBuilder());
        write(builder);
        try {
            System.out.println("Writing plugin.yml...");
            if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if(!file.exists()) file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(builder.build().toString().getBytes(encoding));
            outputStream.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encoded(String encoding) {
        this.encoding = encoding;
    }

    public void descriptionFile(File file) {
        this.file = file;
    }

    public void descriptionFile(String file) {
        descriptionFile(new File(file));
    }

    public void named(String name) {
        this.name = name;
    }

    public void main(String path) {
        this.main = path;
    }

    public void version(String version) {
        this.version = Optional.ofNullable(version);
    }

    public void apiVersion(String apiVersion) {
        this.apiVersion = Optional.ofNullable(apiVersion);
    }

    public void authors(String... authors) {
        this.authors.addAll(Arrays.asList(authors));
    }

    public void description(String description) {
        this.description = Optional.ofNullable(description);
    }

    public void website(String website) {
        this.website = Optional.ofNullable(website);
    }

    public void depend(String... dependencies) {
        this.depend.addAll(Arrays.asList(dependencies));
    }

    public void softDepend(String... dependencies) {
        this.softDepend.addAll(Arrays.asList(dependencies));
    }

    public void loadBefore(String... plugins) {
        this.loadBefore.addAll(Arrays.asList(plugins));
    }

    public void prefixed(String prefix) {
        this.prefix = Optional.ofNullable(prefix);
    }

    public void command(Closure<Command> c) {
        Command command = new Command();
        ConfigureUtil.configure(c, command);
        this.commands.add(command);
    }

    public void command(Action<Command> action) {
        Command command = new Command();
        action.execute(command);
        this.commands.add(command);
    }

    public void permission(Closure<Permission> c) {
        Permission permission = new Permission();
        ConfigureUtil.configure(c, permission);
        this.permissions.add(permission);
    }

    public void permission(Action<Permission> action) {
        Permission permission = new Permission();
        action.execute(permission);
        this.permissions.add(permission);
    }

}
