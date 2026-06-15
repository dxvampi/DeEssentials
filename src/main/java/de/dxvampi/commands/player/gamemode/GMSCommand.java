package de.dxvampi.commands.player.gamemode;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GMSCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;
    public GMSCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        boolean isPlayer = sender instanceof Player;

        if (!isPlayer && args.length == 0) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can only use it targeting online players");
            return true;
        }

        if (!sender.hasPermission("deessentials.gamemode.*")) {
            if (!sender.hasPermission("deessentials.gamemode.survival")) {
                CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
                return true;
            }
        }

        Player to_change;

        if (args.length == 0) {
            to_change = (Player) sender;
        } else {
            to_change = Bukkit.getPlayer(args[0]);
        }

        if (to_change == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }

        to_change.setGameMode(GameMode.SURVIVAL);
        to_change.sendMessage(MessageUtils.getColoredMessage("&aYour gamemode has been set to &e" + "survival"));
        if (to_change != sender) {
            sender.sendMessage(MessageUtils.getColoredMessage("&aYou have changed &f" + to_change.getName() + "&a's gamemode to &e" + "survival"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.hasPermission("deessentials.gamemode.*")) {
            if (!sender.hasPermission("deessentials.gamemode.survival")) {
                return List.of();
            }
        }
        List<String> completions = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            completions.add(player.getName());
        }
        return completions;
    }
}
