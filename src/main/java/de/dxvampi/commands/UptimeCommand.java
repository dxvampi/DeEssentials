package de.dxvampi.commands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jspecify.annotations.NonNull;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class UptimeCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public UptimeCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.hasPermission("deessentials.uptime")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return false;
        }
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();

        long msUptime = rb.getUptime();

        long days = msUptime / (24 * 60 * 60 * 1000);
        long hours = (msUptime % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutes = (msUptime % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (msUptime % (60 * 1000)) / 1000;

        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "Uptime of &f" + days + " days, " + hours +
                " hours, " + minutes + " minutes and "+ seconds + " seconds&a."));

        return false;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return List.of();
    }
}
