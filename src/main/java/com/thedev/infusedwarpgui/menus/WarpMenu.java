package com.thedev.infusedwarpgui.menus;

import com.earth2me.essentials.Essentials;
import com.thedev.infusedwarpgui.InfusedWarpGUI;
import com.thedev.infusedwarpgui.utils.ColorUtil;
import com.thedev.infusedwarpgui.utils.ItemBuilder;
import com.thedev.infusedwarpgui.utils.WarpItem;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WarpMenu {

    private final InfusedWarpGUI plugin;

    private PaginatedGui gui;

    public WarpMenu(InfusedWarpGUI plugin) {
        this.plugin = plugin;
        createMenu();
    }

    public void openMenu(Player player) {
        gui.open(player);
    }

    private void createMenu() {
        gui = Gui.paginated()
                .title(Component.text(ColorUtil.color(plugin.getConfig().getString("menu-name"))))
                .rows(3)
                .create();

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)));

        // The ItemStack for the next page button. Below is the inventory click event action.
        GuiItem nextPage = new GuiItem(new ItemBuilder(Material.ARROW, "&bNext Page", "&7").getItem());
        nextPage.setAction(event -> gui.next());

        // The ItemStack for the previous page button. Below is the inventory click event action.
        GuiItem previousPage = new GuiItem(new ItemBuilder(Material.ARROW, "&bPrevious Page", "&7").getItem());
        previousPage.setAction(event -> gui.previous());

        gui.setItem(26, nextPage);
        gui.setItem(18, previousPage);

        fillWarpSlots(gui);
    }

    // Automatically adds all the warp slots to the menu
    private void fillWarpSlots(PaginatedGui gui) {
        Essentials essentials = plugin.getEssentials();

        for(String warpName : plugin.getConfig().getConfigurationSection("warps").getKeys(false)) {
            if(!essentials.getWarps().isWarp(warpName)) continue;

            ItemStack warpItem = WarpItem.getWarpItem(warpName);

            GuiItem warpGuiItem = getWarpGuiItem(warpName, warpItem);

            gui.addItem(warpGuiItem);
        }
    }

    // Gets the ItemStack with inventory click action
    private GuiItem getWarpGuiItem(String warpName, ItemStack warpItem) {
        String command = "ewarp " + warpName;

        GuiItem warpGuiItem = new GuiItem(warpItem);

        warpGuiItem.setAction(event -> {
            Bukkit.dispatchCommand(event.getWhoClicked(), command);
            event.getWhoClicked().closeInventory();

            if(!(event.getWhoClicked() instanceof Player)) return;
            if(!plugin.getConfig().getBoolean("sounds")) return;

            Player player = (Player) event.getWhoClicked();
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 7, 6);
                });
        return warpGuiItem;
    }
}
