package fr.il_totore.manadrop.util;

import java.io.File;

public enum MinecraftOS {

    WINDOWS("windows", new File(System.getProperty("user.home") + "/AppData/Roaming/.minecraft/"), "bat"),
    LINUX("linux", new File(System.getProperty("user.home") + "/.minecraft/"), "sh"),
    MAC("os x", new File(System.getProperty("user.home") + "/Library/Application Support/minecraft/"), "sh");

    private String name;
    private File minecraftHome;
    private String scriptType;

    MinecraftOS(String name, File minecraftHome, String scriptType) {
        this.name = name;
        this.minecraftHome = minecraftHome;
        this.scriptType = scriptType;
    }

    public File getMinecraftHome() {
        return minecraftHome;
    }

    public String getName() {
        return name;
    }

    public String getScriptType() {
        return scriptType;
    }

    public static MinecraftOS getByName(String name) {
        for(MinecraftOS os : values()) {
            if(name.toLowerCase().contains(os.getName())) return os;
        }
        return LINUX;
    }
}
