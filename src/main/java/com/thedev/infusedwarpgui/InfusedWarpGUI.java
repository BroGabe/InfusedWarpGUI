package com.thedev.infusedwarpgui;

import com.earth2me.essentials.Essentials;
import com.thedev.infusedwarpgui.listeners.CommandListeners;
import com.thedev.infusedwarpgui.menus.WarpMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfusedWarpGUI extends JavaPlugin {

    private static InfusedWarpGUI inst;

    private Essentials essentials;

    private WarpMenu warpMenu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        inst = this;

        saveDefaultConfig();

        if(!Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            Bukkit.getLogger().info("[InfusedWarpGUI] Essentials not found! Plugin disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

        warpMenu = new WarpMenu(this);

        Bukkit.getPluginManager().registerEvents(new CommandListeners(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public WarpMenu getWarpMenu() {
        return warpMenu;
    }


    public static InfusedWarpGUI getInst() {
        return inst;
    }

    public Essentials getEssentials() {
        return essentials;
    }

}
