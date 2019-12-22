package fr.il_totore.lavadrop.yaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlSequenceBuilder;

import java.util.Collection;

public class YamlUtil {

    public static YamlSequenceBuilder fromCollection(Collection<String> strings) {
        EditableYamlSequenceBuilder builder = new EditableYamlSequenceBuilder(Yaml.createYamlSequenceBuilder());
        strings.forEach(builder::add);
        return builder;
    }
}
