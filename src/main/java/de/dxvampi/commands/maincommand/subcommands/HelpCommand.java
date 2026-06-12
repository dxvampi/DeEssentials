package de.dxvampi.commands.maincommand.subcommands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {

    public HelpCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l ---- &a&l" + plugin.getPluginName() + " &f&lCOMMANDS ----"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /deessentials help: &fShows a help menu"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /deessentials date: &fShows current date and uptime"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /deessentials get <author/version/full/name>: &f" + "Shows information about the plugin"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /deessentials welcome: &f" + "Welcomes the user"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /uptime: &f" + "Shows the uptime of the server"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l ---- &a&l" + plugin.getPluginName() + " &f&lCOMMANDS ----"));
    }
}
