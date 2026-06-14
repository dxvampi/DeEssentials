package de.dxvampi.commands.base;

import de.dxvampi.DeEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    protected final DeEssentials plugin;
    protected final CommandSender sender;
    protected final Command command;
    protected final String label;
    protected final String[] args;


    public SubCommand(DeEssentials plugin, CommandSender sender, Command command, String label, String[] args) {
        this.plugin = plugin;
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public abstract void execute();
    public abstract List<String> onTabComplete();
}
