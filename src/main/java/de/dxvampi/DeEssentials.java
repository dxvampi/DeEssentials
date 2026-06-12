package de.dxvampi;

import de.dxvampi.commands.PingCommand;
import de.dxvampi.commands.UptimeCommand;
import de.dxvampi.commands.maincommand.MainCommand;
import de.dxvampi.listeners.PlayerListener;
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
            registerEvents();
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

    private void registerCommands() {
        MainCommand mainCommand = new MainCommand(this);
        Objects.requireNonNull(this.getCommand("deessentials")).setExecutor(mainCommand);
        Objects.requireNonNull(this.getCommand("deessentials")).setTabCompleter(mainCommand);

        Objects.requireNonNull(this.getCommand("ping")).setExecutor(new PingCommand());

        Objects.requireNonNull(this.getCommand("uptime")).setExecutor(new UptimeCommand(this));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
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


