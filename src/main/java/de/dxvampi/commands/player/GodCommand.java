package de.dxvampi.commands.player;

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

public class GodCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public GodCommand (DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        boolean isConsole = !(sender instanceof Player);

        if (!sender.hasPermission("deessentials.god")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        if (args.length == 0) {
            if (isConsole) {
                CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can only use this targeting other players");
                return true;
            }
            Player player = (Player) sender;
            if (!player.isInvulnerable()) {
                player.setInvulnerable(true);
                sender.sendMessage(MessageUtils.getColoredMessage("&aYou are now &e&l" + "invincible"));
            }
            else {
                player.setInvulnerable(false);
                sender.sendMessage(MessageUtils.getColoredMessage("&cYou are no longer invincible"));
            }
            return true;
        }

        if (!sender.hasPermission("deessentials.god.others")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args, "You can only use this targeting yourself");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }

        if (!target.isInvulnerable()) {
            target.setInvulnerable(true);
            target.sendMessage(MessageUtils.getColoredMessage("&aYou are now &e&l" + "invincible"));
            if (target != sender) {
                sender.sendMessage(MessageUtils.getColoredMessage("&aYou set &f" + target.getName() + "&a invincibility to &e" + "true"));
            }
        }
        else {
            target.setInvulnerable(false);
            target.sendMessage(MessageUtils.getColoredMessage("&cYou are no longer invincible"));
            if (target != sender) {
                sender.sendMessage(MessageUtils.getColoredMessage("&aYou set &f" + target.getName() + "&a invincibility to &c" + "false"));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.hasPermission("deessentials.god.others")) {
            return List.of();
        }

        if (args.length > 1) {
            return List.of();
        }

        List<String> completions = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                completions.add(player.getName());
            }
        }
        return completions;
    }
}
