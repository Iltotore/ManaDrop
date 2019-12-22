package fr.il_totore.lavadrop.yaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import com.amihaiemil.eoyaml.YamlNode;

public class EditableYamlMappingBuilder implements YamlMappingBuilder {

    private YamlMappingBuilder builder;

    public EditableYamlMappingBuilder(YamlMappingBuilder builder) {
        this.builder = builder;
    }


    @Override
    public YamlMappingBuilder add(String key, String value) {
        return builder = builder.add(key, value);
    }

    @Override
    public YamlMappingBuilder add(YamlNode key, String value) {
        return builder = builder.add(key, value);
    }

    @Override
    public YamlMappingBuilder add(YamlNode key, YamlNode value) {
        return builder = builder.add(key, value);
    }

    @Override
    public YamlMappingBuilder add(String key, YamlNode value) {
        return builder = builder.add(key, value);
    }

    @Override
    public YamlMapping build() {
        return builder.build();
    }
}
