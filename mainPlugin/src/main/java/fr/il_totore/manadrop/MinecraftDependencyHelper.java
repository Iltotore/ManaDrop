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

    private static String getPaperGroup(String version) {
        int majorVersion = Integer.parseInt(version.split(":")[1]);
        return majorVersion >= 16 ? "io.papermc.paper" : "com.destroystokyo.paper";
    }

    public static String paperApi(String version) {
        return getPaperGroup(version) + ":paper-api:" + version + "-R0.1-SNAPSHOT";
    }

    public static String waterfallApi(String version) {
        return "io.github.waterfallmc:waterfall-api:" + version + "-SNAPSHOT";
    }
}
