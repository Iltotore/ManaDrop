package fr.il_totore.manadrop.mcp;

import fr.il_totore.manadrop.util.MinecraftOS;

import java.io.File;

public class MCPExtension {

    private String clientModule = "eclipse:Client";
    private String serverModule = "eclipse:Server";
    private File minecraftHome;

    {
        setMinecraftHome(MinecraftOS.getByName(System.getProperty("os.name")));
    }

    public String getClientModule() {
        return clientModule;
    }

    public void setClientModule(String clientModule) {
        this.clientModule = clientModule;
    }

    public String getServerModule() {
        return serverModule;
    }

    public void setServerModule(String serverModule) {
        this.serverModule = serverModule;
    }

    public File getMinecraftHome() {
        return minecraftHome;
    }

    public void setMinecraftHome(File minecraftHome) {
        this.minecraftHome = minecraftHome;
    }

    public void setMinecraftHome(MinecraftOS os) {
        setMinecraftHome(os.getMinecraftHome());
    }
}
