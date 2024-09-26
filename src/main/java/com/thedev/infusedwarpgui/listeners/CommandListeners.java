package com.thedev.infusedwarpgui.listeners;

import com.thedev.infusedwarpgui.InfusedWarpGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListeners implements Listener {

    private final InfusedWarpGUI plugin;

    public CommandListeners(InfusedWarpGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().toLowerCase();

        if(!command.equalsIgnoreCase("/warps") && !command.equalsIgnoreCase("/warp")) return;

        plugin.getWarpMenu().openMenu(event.getPlayer());

        event.setCancelled(true);
    }
}
