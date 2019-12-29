package fr.il_totore.manadrop.util;

import java.io.File;

public enum MinecraftOS {

    WINDOWS("windows", new File(System.getProperty("user.home") + "/AppData/Roaming/.minecraft/")),
    LINUX("linux", new File(System.getProperty("user.home") + "/.minecraft/")),
    MAC("os x", new File(System.getProperty("user.home") + "/Library/Application Support/minecraft/"));

    private String name;
    private File minecraftHome;

    MinecraftOS(String name, File minecraftHome) {
        this.name = name;
        this.minecraftHome = minecraftHome;
    }

    public File getMinecraftHome() {
        return minecraftHome;
    }

    public String getName() {
        return name;
    }

    public static MinecraftOS getByName(String name) {
        for(MinecraftOS os : values()) {
            if(name.toLowerCase().contains(os.getName())) return os;
        }
        return LINUX;
    }
}
