package fr.il_totore.manadrop.vanilla;

import fr.il_totore.manadrop.util.DefaultOptional;
import org.gradle.api.Project;

import java.io.File;

public class VanillaModule {

    private String module = "Default";
    private DefaultOptional<File> dir = DefaultOptional.empty();
    private String mappingHash = null;
    private String mappingType = "client";
    private DefaultOptional<String> mappingUrl = DefaultOptional.empty(String.class)
            .defaultValue(() -> "https://launcher.mojang.com/v1/objects/" + mappingHash + "/" + mappingType + ".txt");

    public VanillaModule() {
    }

    public VanillaModule(Project project, String mappingType) {
        dir.defaultValue(() -> project.file(module.replace(".", "/")));
        this.mappingType = mappingType;
    }

    public VanillaModule(Project project, String mappingType, String module) {
        this(project, mappingType);
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public File getDir() {
        return dir.getOrDefault();
    }

    public void setDir(File dir) {
        this.dir = DefaultOptional.ofNullable(dir).defaultValue(this.dir.getDefaultValue());
    }

    public String getMappingHash() {
        return mappingHash;
    }

    public void mappingHash(String mappingHash) {
        this.mappingHash = mappingHash;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void mappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getMappingUrl() {
        return mappingUrl.getOrDefault();
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = DefaultOptional.ofNullable(mappingUrl).defaultValue(this.mappingUrl.getDefaultValue());
    }
}
