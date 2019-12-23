![banner](logo/banner.png)
A Gradle plugin for Minecraft development.

# Description
ManaDrop is a Gradle plugin designed to help Minecraft developers.
It help automatising some tasks and make project more portable.

# Features
Here is main features of this plugin. For more details about them, check the [wiki](https://github.com/Iltotore/ManaDrop/wiki/)

## Spigot
### NMS Dependencies
You can use the `buildTools` to install automatically NMS dependencies in your local maven repository.
```gradle
buildTools {
    versions '1.8.8', '1.9', '1.12.2' //etc...
}
```

Note this task will not install already installed versions

### Build Plugin
You can build your Spigot plugin using the task `spigotPlugin`.
This feature support only the [plugin.yml](https://www.spigotmc.org/wiki/plugin-yml/) generation allowing user to set dynamic parameters like
using the project's name or the project's version.
All attributes are supported.

Here is an example of description generation from the build.gradle.
```gradle
spigot {
    desc {
        named 'TestPlugin' //Default: the project name
        version project.version //Default: the project version
        authors 'Il_totore'
        main 'fr.il_totore.entitymetadata.plugin.Main'

        command {
            named 'test'
            description 'A test command'
            aliases 'tst', 'thisisanalias'
        }

        permission {
            named 'perm.*'
            description 'All perms'
            defaultType 'grant_op'
            child {
                named 'perm.firstChild'
                inherit true
            }

            child {
                named 'perm.secondChild'
                inherit false
            }
        }

        permission {
            named 'perm.firstChild'
            description 'The first one'
            defaultType 'grant_player'
        }

        permission {
            named 'perm.secondChild'
            description 'Luigi'
            defaultType 'deny_player'
        }
    }
}
```

Simply add this task after the `processResources` task.
```gradle
processResources.finalizedBy(spigotPlugin)
```

## Bungeecord
### Build Plugin
You can build your Bungeecord plugin using the task `bungeePlugin`.
Like `spigotPlugin`, it only support plugin description (bungee.yml).
Attributes are same except `api-version` and `load` which are Spigot-only.

## Does this plugin will only support Spigot and Bungeecord ?
This plugin actually support Bungeecord and Spigot (including forks like Paper or Waterfall)
but it will support Sponge, MCP and Forge. Other solutions can be supported if you have an idea.


# Use in your project
You can use this plugin in your Gradle build by applying it.
- Using `apply plugin: fr.il_totore.manadrop:version`

- Using the new `plugins` statement
```gradle
plugins {
   id 'fr.il_totore.manadrop' version 'version'
}
```