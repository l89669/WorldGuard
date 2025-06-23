//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sk89q.worldguard.bukkit.commands;

import cc.summermc.engine.server.BasicAPI;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.forge.ForgePlayer;
import com.sk89q.worldedit.util.command.parametric.ArgumentStack;
import com.sk89q.worldedit.util.command.parametric.BindingBehavior;
import com.sk89q.worldedit.util.command.parametric.BindingHelper;
import com.sk89q.worldedit.util.command.parametric.BindingMatch;
import com.sk89q.worldedit.util.command.parametric.ParameterException;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;

public class BukkitBinding extends BindingHelper {

    private static final Field f_ForgePlayer_player;

    static {
        Field f = null;
        try {
            f = ForgePlayer.class.getDeclaredField("player");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f_ForgePlayer_player = f;
    }

    @BindingMatch(
            type = {CommandSender.class},
            behavior = BindingBehavior.PROVIDES
    )
    public CommandSender getCommandSender(ArgumentStack context) throws ParameterException {
        Actor sender = (Actor)context.getContext().getLocals().get(Actor.class);
        if (sender == null) {
            throw new ParameterException("No player to get a session for");
        } else if (sender instanceof ForgePlayer) {
            return transform((ForgePlayer) sender);
        } else {
            throw new ParameterException("Caller is not a player");
        }
    }

    private static CommandSender transform(ForgePlayer player) throws ParameterException {
        if (f_ForgePlayer_player != null) {
            try {
                return BasicAPI.transform((EntityPlayerMP) f_ForgePlayer_player.get(player));
            } catch (IllegalAccessException e) {
                throw new ParameterException("Failed to access player field", e);
            }
        } else {
            throw new ParameterException("ForgePlayer field is not accessible");
        }
    }
}
