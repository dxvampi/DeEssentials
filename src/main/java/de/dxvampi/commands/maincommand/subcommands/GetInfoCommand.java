package de.dxvampi.commands.maincommand.subcommands;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.commands.base.SubCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class GetInfoCommand extends SubCommand {
    public GetInfoCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        super(plugin, sender, command, label, args);
    }

    public String permission = "deessentials.maincommand.get";

    @Override
    public void execute() {
        if (!sender.hasPermission(permission)) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return;
        }
        if (args.length < 2) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cCommand usage: &7/" + label + " get <author/name/version/full>"));
            return;
        }
        switch (args[1]) {
            case "author":
                sender.sendMessage(MessageUtils.getColoredMessage("&fThe author of the plugin is &a" + plugin.getAuthor().toString()));
                break;
            case "version":
                sender.sendMessage(MessageUtils.getColoredMessage("&fCurrently running &av" + plugin.getVersion()));
                break;
            case "name":
                sender.sendMessage(MessageUtils.getColoredMessage("&fThe name of the plugin is &a" + plugin.getPluginName()));
                break;
            case "full":
                sender.sendMessage(MessageUtils.getColoredMessage("&fRunning &a" + plugin.getPluginName() + " &fby &a" + plugin.getAuthor() + "&f (&av" + plugin.getVersion() + "&f)"));
                break;
            default:
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cCommand usage: &7/" + label + " get <author/name/version/full>"));
                break;
        }
    }

    public List<String> onTabComplete() {
        List<String> completions = new ArrayList<>();

        if (!sender.hasPermission(permission)) return completions;

        if (args.length == 2) {
            List<String> subcommands = List.of("author", "version", "name", "full");

            String currentArg = args[1].toLowerCase();

            for (String sub : subcommands) {
                if (sub.startsWith(currentArg)) {
                    completions.add(sub);
                }
            }
        }

        return completions;
    }


}
