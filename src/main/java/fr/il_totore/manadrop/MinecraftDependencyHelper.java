package fr.il_totore.manadrop;

public class MinecraftDependencyHelper {

    public static String spigotApi(String version) {
        return "org.spigotmc:spigot-api:" + version + "-R0.1-SNAPSHOT";
    }

    public static String spigot(String version) {
        return "org.spigotmc:spigot:" + version + "-R0.1-SNAPSHOT";
    }

    public static String bukkitApi(String version) {
        return "org.bukkit:bukkit:" + version + "-R0.1-SNAPSHOT";
    }

    public static String craftbukkit(String version) {
        return "org.bukkit:craftbukkit" + version + "-R0.1-SNAPSHOT";
    }

    public static String bungeecordApi(String version) {
        return "net.md-5:bungeecord-api:" + version + "-SNAPSHOT";
    }

    public static String paperApi(String version) {
        return "com.destroystokyo.paper:paper-api:" + version + "-R0.1-SNAPSHOT";
    }

    public static String waterfallApi(String version) {
        return "com.destroystokyo.paper:waterfall-api:" + version + "-SNAPSHOT";
    }
}
