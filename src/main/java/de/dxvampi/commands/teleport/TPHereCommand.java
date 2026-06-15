package de.dxvampi.commands.teleport;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TPHereCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;
    public TPHereCommand (DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        boolean isPlayer = sender instanceof Player;

        if(!sender.hasPermission("deessentials.tphere")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        if (!isPlayer) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args);
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cInvalid usage, do &7/" + label + "<player>" +
                    "&c instead"));
            return true;
        }

        Player target = (Player) sender;
        Player toTeleport = Bukkit.getPlayer(args[0]);

        if(toTeleport == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }

        if (target == toTeleport) {
            target.sendMessage(MessageUtils.getColoredMessage("&cYou can't teleport to yourself"));
            return true;
        }

        toTeleport.teleport(target);
        target.sendMessage(MessageUtils.getColoredMessage("&aTeleported &f" + toTeleport.getDisplayName() + "&a to you"));
        toTeleport.sendMessage(MessageUtils.getColoredMessage("&aYou have been teleported to &f" + target.getDisplayName()));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if (args.length > 1) {
            return List.of();
        }

        List<String> playerList = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(sender.getName())) {
                continue;
            }
            if (p.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                playerList.add(p.getName());
            }
        }

        return playerList;
    }
}
