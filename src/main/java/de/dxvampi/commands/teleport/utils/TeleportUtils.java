package de.dxvampi.commands.teleport.utils;

import de.dxvampi.DeEssentials;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;

public class TeleportUtils {
    public static void teleportWithTime(DeEssentials plugin, Player player, Location target) {
        String group = plugin.getPermissionProvider().getGroup(player);

        int waitTime = plugin.getConfig().getInt("deessentials.teleports.wait_time.group." + group, 5);
        if (waitTime <= 0) {
            executeTeleportAction(plugin, player, target);
            return;
        }

        player.sendMessage(MessageUtils.getColoredMessage("&aTeleporting in &f" + waitTime + " seconds"));
        Bukkit.getScheduler().runTaskLater(plugin, () -> executeTeleportAction(plugin, player, target), waitTime * 20L);
    }

    private static void executeTeleportAction(DeEssentials plugin, Player player, Location target) {
        player.teleport(target);
        player.sendMessage(MessageUtils.getColoredMessage("&aYou have been teleported"));

        boolean doSound = plugin.getConfig().getBoolean("deessentials.teleports.do_sound", true);
        if (doSound) {
            player.playNote(target, Instrument.BELL, Note.sharp(1, Note.Tone.A));
        }
    }

}
