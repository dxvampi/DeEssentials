package de.dxvampi.commands.maincommand;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.commands.maincommand.subcommands.DateCommand;
import de.dxvampi.commands.maincommand.subcommands.GetInfoCommand;
import de.dxvampi.commands.maincommand.subcommands.HelpCommand;
import de.dxvampi.commands.maincommand.subcommands.WelcomeCommand;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public MainCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (sender instanceof Player && !(sender.hasPermission("deessentials.maincommand"))) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return false;
        }

        if(args.length >= 1) {
            switch(args[0].toLowerCase()) {
                case "date": new DateCommand(plugin, sender, command, label, args).execute(); break;
                case "help": new HelpCommand(plugin, sender, command, label, args).execute(); break;
                case "greet":
                case "welcome": new WelcomeCommand(plugin, sender, command, label, args).execute(); break;
                case "get": new GetInfoCommand(plugin, sender, command, label, args).execute(); break;
                default: sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&cSub-command &7/" + label + " " +
                        args[0] + "&c does not exist"));
            }
        } else {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aRunning correctly! Version: &c"+plugin.getVersion()+"&a."));
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        List<String> completions = new ArrayList<>();
        if (sender instanceof Player && (!sender.hasPermission("deessentials.maincommand"))) {
            return completions;
        }
        if (args.length == 1) {
            List<String> subcommands = List.of("date", "help", "get", "greet", "welcome");

            String currentArg = args[0].toLowerCase();

            for (String sub : subcommands) {
                if (sub.startsWith(currentArg)) {
                    completions.add(sub);
                }
            }
        }

        if (args.length > 1) {
            if (args[0].equals("get")) {
                completions = new GetInfoCommand(plugin, sender, command, label, args).onTabComplete();
            }
        }

        return completions;
    }
}
