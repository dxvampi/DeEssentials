package de.dxvampi;

import de.dxvampi.commands.UptimeCommand;
import de.dxvampi.commands.maincommand.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.dxvampi.utils.MessageUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeEssentials extends JavaPlugin {

    private final String name = getName();
    private final String prefix = "&8[&a&l" + getName() + "&8] ";
    private final String version = getDescription().getVersion();
    private final List<String> author = getDescription().getAuthors();

    @Override
    public void onEnable() {
        try {
            registerCommands();
        }
        catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("Could not initialize " + name + "."));
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&cError: " + e));
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&c&l" + Arrays.toString(e.getStackTrace())));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(prefix + "&ahas been enabled! &8Version: &f" + version));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage(prefix + "&c" + "has been disabled, ciao!"));
    }

    public void registerCommands() {
        Objects.requireNonNull(this.getCommand("deessentials")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(this.getCommand("deessentials")).setTabCompleter(new MainCommand(this));

        Objects.requireNonNull(this.getCommand("uptime")).setExecutor(new UptimeCommand(this));
    }

    // GETTER METHODS

    public String getPrefix() {
        return prefix;
    }

    public String getPluginName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getAuthor() {
        return author;
    }
}


