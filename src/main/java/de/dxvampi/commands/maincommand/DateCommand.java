package de.dxvampi.commands.maincommand;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCommand extends SubCommand {

    public DateCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String actualDate = LocalDateTime.now().format(formatter);
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();

        long msUptime = rb.getUptime();

        long days = msUptime / (24 * 60 * 60 * 1000);
        long hours = (msUptime % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutes = (msUptime % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (msUptime % (60 * 1000)) / 1000;

        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aThe current server date is: &f" + actualDate + "&a. With a server " +
                "uptime of &f" + days + " days and " + hours + " hours&a."));
    }
}
