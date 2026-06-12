package de.dxvampi.commands.maincommand.subcommands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WelcomeCommand extends SubCommand {

    public WelcomeCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cCommand&f /" + label + " " + args[0] + "&c can't be used in the console"));
            return;
        }

        Player player = ((Player) sender).getPlayer();
        assert player != null;
        player.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aHello, &f" + player.getDisplayName() + "&a!"));
    }
}
