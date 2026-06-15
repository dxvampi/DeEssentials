package de.dxvampi.commands.maincommand.subcommands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends SubCommand {
    public ReloadCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    @Override
    public void execute() {
        if(!sender.hasPermission("deessentials.maincommand.reload")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return;
        }

        plugin.reloadConfig();

        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aConfig reloaded successfully!"));
    }

    @Override
    public List<String> onTabComplete() {
        return List.of();
    }
}
