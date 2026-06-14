package de.dxvampi.commands.inventory;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class InvSeeCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;
    public InvSeeCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!(sender instanceof Player)) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args);
            return true;
        }
        if(!sender.hasPermission("deessentials.invsee")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }
        Player toSee = Bukkit.getPlayer(args[0]);
        if(toSee == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }

        ((Player) sender).openInventory(toSee.getInventory());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!sender.hasPermission("deessentials.invsee")) {
            return List.of();
        }
        if(args.length > 1) {
            return List.of();
        }
        List<String> players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(player.getName());
        }
        return players;
    }
}
