package de.dxvampi.commands;

import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cYou can't use &7/" + label + " &cin the console"));
            return false;
        }
        Player p = (Player) sender;
        p.sendMessage(p.getPing() + "ms");
        return true;
    }
}
