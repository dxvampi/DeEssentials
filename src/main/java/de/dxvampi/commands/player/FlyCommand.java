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

public class FlyCommand implements CommandExecutor, TabCompleter {

    private DeEssentials plugin;

    public FlyCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (!sender.hasPermission("deessentials.fly")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        boolean isPlayer = sender instanceof Player;

        if (args.length == 0) {
            if(!isPlayer) {
                CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can only give others " +
                        " the ability to fly");
                return true;
            }

            Player player = (Player) sender;
            switchFly(player, sender);
            return true;
        }

        if (!sender.hasPermission("deessentials.fly.others")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args, "You can only apply the command" +
                    " to yourself");
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }
        switchFly(target, sender);

        return true;
    }

    private void switchFly (Player player, CommandSender sender) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(MessageUtils.getColoredMessage("&aYou can no longer fly"));
            if (sender != player) {
                sender.sendMessage(MessageUtils.getColoredMessage("&f" + player.getDisplayName() + "&a can't fly now"));
            }
        }
        else {
            player.setAllowFlight(true);
            player.sendMessage(MessageUtils.getColoredMessage("&aYou can fly now!"));
            if (sender != player) {
                sender.sendMessage(MessageUtils.getColoredMessage("&f" + player.getDisplayName() + "&a can fly now"));
            }
        }

    }

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
