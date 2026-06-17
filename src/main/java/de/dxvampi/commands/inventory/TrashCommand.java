package de.dxvampi.commands.inventory;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class TrashCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public TrashCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!(sender instanceof Player p)) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args);
            return true;
        }

        if (!sender.hasPermission("deessentials.trash")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        Inventory trash = Bukkit.createInventory(null, 36, MessageUtils.getColoredMessage("&4&lTrash Bin"));

        p.openInventory(trash);

        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return List.of();
    }
}
