package com.thedev.infusedwarpgui.utils;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;
import com.thedev.infusedwarpgui.InfusedWarpGUI;

import java.util.ArrayList;
import java.util.List;

public class EssentialsUtils {

    public static boolean isWarp(String warpName) {
        InfusedWarpGUI plugin = InfusedWarpGUI.getInst();
        Essentials essentials = plugin.getEssentials();

        Warps warps = essentials.getWarps();

        List<String> warpNames = new ArrayList<>(warps.getList());

        return warpNames.stream().anyMatch(essentialsWarp -> essentialsWarp.equalsIgnoreCase(warpName));
    }
}
