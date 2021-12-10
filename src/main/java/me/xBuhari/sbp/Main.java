package me.xBuhari.sbp;

import me.xBuhari.sbp.sonBagisci.PapiExp;
import me.xBuhari.sbp.sonBagisci.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main plugin;
    private SBManager sbManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        this.sbManager = new SBManager();
        if (Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null) {
            new PapiExp().register();
        }
    }

    public SBManager getSbManager() {
        return sbManager;
    }

    public static Main getPlugin() {
        return plugin;
    }
}
