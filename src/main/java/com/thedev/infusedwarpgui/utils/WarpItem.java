package com.thedev.infusedwarpgui.utils;

import com.thedev.infusedwarpgui.InfusedWarpGUI;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WarpItem {

    public static ItemStack getWarpItem(String warpName) {
        ConfigurationSection configSection = InfusedWarpGUI.getInst().getConfig().getConfigurationSection("warps." + warpName);

        List<String> lore = configSection.getStringList("lore");

        Material material = Material.valueOf(configSection.getString("material"));
        String name = configSection.getString("name");
        String texture = configSection.getString("texture");
        int data = configSection.getInt("data");

        return new ItemBuilder(material, name, 1, data, texture, lore).getItem();
    }
}
