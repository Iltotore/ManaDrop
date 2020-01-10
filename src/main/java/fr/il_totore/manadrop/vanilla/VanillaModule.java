package fr.il_totore.manadrop.vanilla;

import fr.il_totore.manadrop.util.DefaultOptional;
import org.gradle.api.Project;

import java.io.File;

public class VanillaModule {

    private String module = "Default";
    private DefaultOptional<File> dir = DefaultOptional.empty();
    private String mappingHash = "";
    private String mappingType = "client";
    private DefaultOptional<String> mappingUrl = DefaultOptional.empty(String.class)
            .defaultValue(() -> "https://launcher.mojang.com/v1/objects/" + mappingHash + "/" + mappingType + ".json");

    public VanillaModule() {
    }

    public VanillaModule(Project project) {
        dir.defaultValue(() -> project.file(module.replace(".", "/")));
    }

    public VanillaModule(Project project, String module) {
        this(project);
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public File getDir() {
        return dir.get();
    }

    public void setDir(File dir) {
        this.dir = DefaultOptional.ofNullable(dir).defaultValue(this.dir.getDefaultValue());
    }

    public String getMappingHash() {
        return mappingHash;
    }

    public void setMappingHash(String mappingHash) {
        this.mappingHash = mappingHash;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getMappingUrl() {
        return mappingUrl.getOrDefault();
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = DefaultOptional.ofNullable(mappingUrl).defaultValue(this.mappingUrl.getDefaultValue());
    }
}
