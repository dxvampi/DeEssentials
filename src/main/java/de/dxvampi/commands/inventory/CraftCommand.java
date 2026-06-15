package de.dxvampi.commands.inventory;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class CraftCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;
    public CraftCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args);
            return true;
        }
        if (!sender.hasPermission("deessentials.craft")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }
        player.openWorkbench(null, true);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return List.of();
    }
}
