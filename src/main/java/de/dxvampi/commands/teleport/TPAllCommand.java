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

public class TPAllCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public TPAllCommand (DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        boolean isPlayer = sender instanceof Player;

        if (!sender.hasPermission("deessentials.tpall")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        if (args.length == 0) {
            if (!isPlayer) {
                CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can specify a player " +
                        "and everyone will teleport to him");
                return true;
            }
            Player target = (Player) sender;
            tpAll(sender, target);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        tpAll(sender, target);
        return true;
    }
    private void tpAll(CommandSender sender, Player target) {
        if (target == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return;
        }
        int playersTeleported = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != target) {
                player.teleport(target.getLocation());
                player.sendMessage(MessageUtils.getColoredMessage("&aTeleporting you to &f" + target.getDisplayName()));
                playersTeleported++;
            }
        }
        sender.sendMessage(MessageUtils.getColoredMessage("&aTeleported &f" + playersTeleported + "&a players to &f" +
                target.getDisplayName()));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            return List.of();
        }

        List<String> playerList = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                playerList.add(p.getName());
            }
        }

        return playerList;
    }
}
