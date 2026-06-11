package de.dxvampi.commands.maincommand;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {

    public HelpCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&c" + "You'll get help in a minute twin"));
    }
}
