package fr.il_totore.manadrop;

public class MinecraftDependency {

    public static String spigotApiDependency(String version) {
        return "org.spigotmc:spigot-api:" + version + "-R0.1-SNAPSHOT";
    }

    public static String spigotDependency(String version) {
        return "org.spigotmc:spigot:" + version + "-R0.1-SNAPSHOT";
    }

    public static String bukkitDependency(String version) {
        return "org.bukkit:bukkit:" + version + "-R0.1-SNAPSHOT";
    }

    public static String craftbukkitDependency(String version) {
        return "org.bukkit:craftbukkit" + version + "-R0.1-SNAPSHOT";
    }

    public static String bungeecordApiDependency(String version) {
        return "net.md-5:bungeecordapi:" + version + "-SNAPSHOT";
    }

    public static String paperApiDependency(String version) {
        return "org.github.paperspigot:paperspigot-api:" + version + "-R0.1-SNAPSHOT";
    }

    public static String waterfallDependency(String version) {
        return "io.github.waterfallmc:waterfall-api:" + version + "-SNAPSHOT";
    }
}
