package de.dxvampi.commands.maincommand.subcommands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WelcomeCommand extends SubCommand {

    public WelcomeCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        if (sender instanceof Player && !sender.hasPermission("deessentials.maincommand.welcome")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cCommand&f /" + label + " " + args[0] + "&c can't be used in the console"));
            return;
        }

        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aHello, &f" + ((Player) sender).getDisplayName() + "&a!"));
    }

    @Override
    public List<String> onTabComplete() {
        return List.of();
    }
}
