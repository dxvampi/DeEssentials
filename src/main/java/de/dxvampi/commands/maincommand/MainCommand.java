package de.dxvampi.commands.maincommand;

import de.dxvampi.DeEssentials;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MainCommand implements CommandExecutor {

    private final DeEssentials plugin;

    public MainCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HelpCommand helpCommand = new HelpCommand(plugin, sender, command, label, args);
        DateCommand dateCommand = new DateCommand(plugin, sender, command, label, args);

        if(args.length >= 1) {
            switch(args[0].toLowerCase()) {
                case "date": dateCommand.execute(); break;
                case "help": helpCommand.execute(); break;
                case "get": break;
                default: sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cSub-command &f" + label + "&c does not exist"));
            }
            if(args[0].equalsIgnoreCase("date")) dateCommand.execute(); // /deessentials date
            if(args[0].equalsIgnoreCase("help")) helpCommand.execute(); // /deessentials help
            if(args[0].equalsIgnoreCase("get")); // /deessentials get
        } else {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&a Running correctly! Version: &c"+plugin.getVersion()+"&a."));
            return true;
        }

        return false;
    }

}
