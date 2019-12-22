package fr.il_totore.lavadrop.yaml;

import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlSequenceBuilder;

public class EditableYamlSequenceBuilder implements YamlSequenceBuilder {

    private YamlSequenceBuilder builder;

    public EditableYamlSequenceBuilder(YamlSequenceBuilder builder) {
        this.builder = builder;
    }

    @Override
    public YamlSequenceBuilder add(String value) {
        return builder = builder.add(value);
    }

    @Override
    public YamlSequenceBuilder add(YamlNode node) {
        return builder = builder.add(node);
    }

    @Override
    public YamlSequence build() {
        return builder.build();
    }
}
