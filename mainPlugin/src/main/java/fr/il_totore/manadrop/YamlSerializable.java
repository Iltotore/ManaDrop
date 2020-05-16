package fr.il_totore.manadrop;

import org.simpleyaml.configuration.ConfigurationSection;

public interface YamlSerializable {

    void write(ConfigurationSection section);
}
