package fr.il_totore.manadrop;

import fr.il_totore.manadrop.optional.OptionalArrayList;
import fr.il_totore.manadrop.optional.OptionalList;
import fr.il_totore.manadrop.task.ChildTask;
import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.util.ConfigureUtil;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
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
    public void write(ConfigurationSection section) {
        if(name == null || name.isEmpty()) throw new NullPointerException("main cannot be null or empty !");
        section.set("name", name);
        if(main == null) throw new NullPointerException("main cannot be null !");
        section.set("main", main);
        version.ifPresent(value -> section.set("version", value));
        authors.ifSingle(value -> section.set("author", value));
        authors.ifSizeSuperior(1, values -> section.set("authors", values));
        description.ifPresent(value -> section.set("description", value));
        website.ifPresent(value -> section.set("website", value));
        depend.ifAllPresent(0, values -> section.set("depend", values));
        softDepend.ifAllPresent(0, values -> section.set("softDepend", values));
        loadBefore.ifAllPresent(0, values -> section.set("loadBefore", values));
        prefix.ifPresent(value -> section.set("prefix", value));
        commands.ifAllPresent(0, commandList -> {
            ConfigurationSection commandSection = section.createSection("commands");
            commandList.forEach(command -> command.write(commandSection));
        });
        permissions.ifAllPresent(0, permList -> {
            ConfigurationSection permSection = section.createSection("permissions");
            permList.forEach(perm -> perm.write(permSection));
        });
    }

    @Override
    public void run(Task parent) {
        System.out.println("Building plugin.yml...");
        YamlConfiguration configuration = new YamlConfiguration();
        write(configuration);
        try {
            System.out.println("Writing plugin.yml...");
            configuration.save(file);
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
