package de.dxvampi.commands.player;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class HatCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public HatCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if(!(sender instanceof Player p)) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args);
            return true;
        }

        if (!p.hasPermission("deessentials.hat")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        ItemStack item = p.getInventory().getItemInMainHand();
        p.getInventory().setHelmet(item);
        p.getInventory().setItemInMainHand(null);

        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return List.of();
    }
}
