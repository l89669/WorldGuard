package com.sk89q.worldguard.bukkit.util;

import cc.summermc.engine.server.BasicAPI;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.forge.ForgeWorldEdit;
import com.sk89q.worldedit.internal.LocalWorldAdapter;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.sk89q.worldedit.LocalWorld;

public class BukkitHelper {

    public static LocalWorld getLocalWorld(World w) {
        val fw = ForgeWorldEdit.inst.getWorld(BasicAPI.transform(w));
        return LocalWorldAdapter.adapt(fw);
    }

    public static Location getBukkitLocation(com.sk89q.worldedit.Location location) {
        World world = Bukkit.getWorld(location.getWorld().getName());
        Vector position = location.getPosition();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        return new Location(world, position.getX(), position.getY(), position.getZ(), yaw, pitch);
    }

    public static Vector toVector(org.bukkit.Location location) {
        return new Vector(location.getX(), location.getY(), location.getZ());
    }

    public static void setSelection(Player player, RegionSelector selector) {
        val world = player.getWorld();
        LocalSession session = WorldEdit.getInstance().getSession(player);
        session.setRegionSelector(world, selector);
        session.dispatchCUISelection(player);
    }

}
