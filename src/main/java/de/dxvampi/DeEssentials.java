package de.dxvampi;

import de.dxvampi.commands.inventory.CraftCommand;
import de.dxvampi.commands.inventory.EnderChestCommand;
import de.dxvampi.commands.inventory.InvSeeCommand;
import de.dxvampi.commands.inventory.TrashCommand;
import de.dxvampi.commands.player.*;
import de.dxvampi.commands.PingCommand;
import de.dxvampi.commands.UptimeCommand;
import de.dxvampi.commands.maincommand.MainCommand;
import de.dxvampi.commands.player.gamemode.GMACommand;
import de.dxvampi.commands.player.gamemode.GMCCommand;
import de.dxvampi.commands.player.gamemode.GMSCommand;
import de.dxvampi.commands.player.gamemode.GMSPCommand;
import de.dxvampi.commands.spawn.SetSpawnCommand;
import de.dxvampi.commands.spawn.SpawnCommand;
import de.dxvampi.commands.teleport.TPAllCommand;
import de.dxvampi.commands.teleport.TPHereCommand;
import de.dxvampi.listeners.GodListener;
import de.dxvampi.listeners.PlayerListener;
import de.dxvampi.utils.permission.*;
import de.dxvampi.utils.permission.providers.BukkitPermissionProvider;
import de.dxvampi.utils.permission.providers.LuckPermsPermissionProvider;
import de.dxvampi.utils.permission.providers.VaultPermissionProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.dxvampi.utils.MessageUtils;

import java.util.*;

public class DeEssentials extends JavaPlugin {

    private PermissionProvider permissionProvider = new BukkitPermissionProvider();
    private final String name = getName();
    private final String prefix = "&8[&a&l" + getName() + "&8] ";
    private final String version = getDescription().getVersion();
    private final List<String> author = getDescription().getAuthors();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        try {
            registerCommands();
            registerEvents();
            setupPermissions();
        }
        catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("Could not initialize " + name + "."));
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&cError: " + e));
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&c" + e));
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
        // MainCommand
        MainCommand mainCommand = new MainCommand(this);
        Objects.requireNonNull(this.getCommand("deessentials")).setExecutor(mainCommand);
        Objects.requireNonNull(this.getCommand("deessentials")).setTabCompleter(mainCommand);

        // Ping command
        PingCommand pingCommand = new PingCommand();
        Objects.requireNonNull(this.getCommand("ping")).setExecutor(pingCommand);
        Objects.requireNonNull(this.getCommand("ping")).setTabCompleter(pingCommand);

        // Uptime command
        UptimeCommand uptimeCommand = new UptimeCommand(this);
        Objects.requireNonNull(this.getCommand("uptime")).setExecutor(uptimeCommand);
        Objects.requireNonNull(this.getCommand("uptime")).setTabCompleter(uptimeCommand);

        // Heal command
        HealCommand healCommand = new HealCommand(this);
        Objects.requireNonNull(this.getCommand("heal")).setExecutor(healCommand);
        Objects.requireNonNull(this.getCommand("heal")).setTabCompleter(healCommand);

        // Feed command
        FeedCommand feedCommand = new FeedCommand(this);
        Objects.requireNonNull(this.getCommand("feed")).setExecutor(feedCommand);
        Objects.requireNonNull(this.getCommand("feed")).setTabCompleter(feedCommand);

        // TPAll command
        TPAllCommand tpAllCommand = new TPAllCommand(this);
        Objects.requireNonNull(this.getCommand("tpall")).setExecutor(tpAllCommand);
        Objects.requireNonNull(this.getCommand("tpall")).setTabCompleter(tpAllCommand);

        // TPHere command
        TPHereCommand tpHereCommand = new TPHereCommand(this);
        Objects.requireNonNull(this.getCommand("tphere")).setExecutor(tpHereCommand);
        Objects.requireNonNull(this.getCommand("tpall")).setTabCompleter(tpHereCommand);

        // Fly command
        FlyCommand flyCommand = new FlyCommand(this);
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(flyCommand);
        Objects.requireNonNull(this.getCommand("fly")).setTabCompleter(flyCommand);

        // SetSpawn command
        SetSpawnCommand setSpawnCommand = new SetSpawnCommand(this);
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(setSpawnCommand);
        Objects.requireNonNull(this.getCommand("setspawn")).setTabCompleter(setSpawnCommand);

        // Spawn command
        SpawnCommand spawnCommand = new SpawnCommand(this);
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(spawnCommand);
        Objects.requireNonNull(this.getCommand("spawn")).setTabCompleter(spawnCommand);

        // Invsee command
        InvSeeCommand invSeeCommand = new InvSeeCommand(this);
        Objects.requireNonNull(this.getCommand("invsee")).setExecutor(invSeeCommand);
        Objects.requireNonNull(this.getCommand("invsee")).setTabCompleter(invSeeCommand);

        // Craft command
        CraftCommand command = new CraftCommand(this);
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(command);
        Objects.requireNonNull(this.getCommand("craft")).setTabCompleter(command);

        // EnderChest command
        EnderChestCommand enderChestCommand = new EnderChestCommand(this);
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(enderChestCommand);
        Objects.requireNonNull(this.getCommand("enderchest")).setTabCompleter(enderChestCommand);

        // GMS command
        GMSCommand gmsCommand = new GMSCommand(this);
        Objects.requireNonNull(this.getCommand("gms")).setExecutor(gmsCommand);
        Objects.requireNonNull(this.getCommand("gms")).setTabCompleter(gmsCommand);

        // GMC command
        GMCCommand gmcCommand = new GMCCommand(this);
        Objects.requireNonNull(this.getCommand("gmc")).setExecutor(gmcCommand);
        Objects.requireNonNull(this.getCommand("gmc")).setTabCompleter(gmcCommand);

        // GMA command
        GMACommand gmaCommand = new GMACommand(this);
        Objects.requireNonNull(this.getCommand("gma")).setExecutor(gmaCommand);
        Objects.requireNonNull(this.getCommand("gma")).setTabCompleter(gmaCommand);

        // GMSP command
        GMSPCommand gmspCommand = new GMSPCommand(this);
        Objects.requireNonNull(this.getCommand("gmsp")).setExecutor(gmspCommand);
        Objects.requireNonNull(this.getCommand("gmsp")).setTabCompleter(gmspCommand);

        // Trash command
        TrashCommand trashCommand = new TrashCommand(this);
        Objects.requireNonNull(this.getCommand("trash")).setExecutor(trashCommand);
        Objects.requireNonNull(this.getCommand("trash")).setTabCompleter(trashCommand);

        // Hat command
        HatCommand hatCommand = new HatCommand(this);
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(hatCommand);
        Objects.requireNonNull(this.getCommand("hat")).setTabCompleter(hatCommand);

        // God command
        GodCommand godCommand = new GodCommand(this);
        Objects.requireNonNull(this.getCommand("god")).setExecutor(godCommand);
        Objects.requireNonNull(this.getCommand("god")).setTabCompleter(godCommand);
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new GodListener(), this);
    }

    private void setupPermissions() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            this.permissionProvider = new LuckPermsPermissionProvider();
            getLogger().info("LuckPerms has been detected, using LuckPerms API for groups");
        }
        else if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            this.permissionProvider = new VaultPermissionProvider();
            getLogger().info("Vault has been detected, using universal group detection");
        }
        else {
            this.permissionProvider = new BukkitPermissionProvider();
            getLogger().info("No supported permission plugin detected! Using fallback.");
        }
    }

    // GETTER METHODS

    public PermissionProvider getPermissionProvider() {
        return this.permissionProvider;
    }

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


