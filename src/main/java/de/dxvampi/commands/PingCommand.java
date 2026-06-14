package de.dxvampi.commands;

import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class PingCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cYou can't use &7/" + label + " &cin the console"));
            return false;
        }
        p.sendMessage(p.getPing() + "ms");
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
            return List.of();
    }
}

