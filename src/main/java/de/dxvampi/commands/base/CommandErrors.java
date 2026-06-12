package de.dxvampi.commands.base;

import de.dxvampi.DeEssentials;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.CommandSender;

public class CommandErrors {
    public static void RaiseInsufficientPermission(DeEssentials plugin, CommandSender sender, String label, String[] args) {
        StringBuilder argStr = new StringBuilder();
        for (String arg : args) {
            argStr.append(arg).append(" ");
        }
        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cYou are not allowed to run &7/" + label + " " + argStr + "&c(Insufficient permission)"));
    }
}
