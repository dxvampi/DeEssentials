package de.dxvampi.commands.spawn;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetSpawnCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public SetSpawnCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        boolean isPlayer = sender instanceof Player;

        // 1 - Permissions
        if (!sender.hasPermission("deessentials.setspawn")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        // 2 - Block console
        if (!isPlayer && args.length != 4 && args.length != 6) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "&cCommand " +
                    "console usage: &7/" + label + " world x y z&c or &7/" + label + " world x y z yaw pitch");
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            saveSpawn(player.getLocation(), player);
            return true;
        }
        if (args.length == 3) {
            double x, y, z;
            try {
                x = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[0] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                y = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[1] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                z = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[2] + "&c is not a valid " +
                        "number"));
                return true;
            }
            Player player = (Player) sender;
            Location loc = new Location(player.getWorld(), x, y, z);
            saveSpawn(loc, player);
            return true;
        }
        if (args.length == 4) {
            double x, y, z;
            World world;

            world = Bukkit.getWorld(args[0]);
            if (world == null) {
                sender.sendMessage(MessageUtils.getColoredMessage("&cUnknown world '&7" + args[0] + "&c'"));
                return true;
            }
            try {
                x = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[1] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                y = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[2] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                z = Double.parseDouble(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[3] + "&c is not a valid " +
                        "number"));
                return true;
            }
            Location loc = new Location(world, x, y, z);
            saveSpawn(loc, sender);
            return true;
        }
        if (args.length == 5) {
            double x, y, z;
            float yaw, pitch;
            try {
                x = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[0] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                y = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[1] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                z = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[2] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                yaw = Float.parseFloat(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[3] + "&c is not a valid " +
                        "number"));
                return true;
            }
            try {
                pitch = Float.parseFloat(args[4]);
            } catch (NumberFormatException e) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[4] + "&c is not a valid " +
                        "number"));
                return true;
            }
            Player player = (Player) sender;
            Location loc = new Location(player.getWorld(), x, y, z, yaw, pitch);
            saveSpawn(loc, player);
            return true;
        }
        double x, y, z;
        float yaw, pitch;
        World world;
        world = Bukkit.getWorld(args[0]);
        if (world == null) {
            sender.sendMessage("&cUnknown world '&7" + args[0] + "&c'");
            return true;
        }
        try {
            x = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[1] + "&c is not a valid " +
                    "number"));
            return true;
        }
        try {
            y = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[2] + "&c is not a valid " +
                    "number"));
            return true;
        }
        try {
            z = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[3] + "&c is not a valid " +
                    "number"));
            return true;
        }
        try {
            yaw = Float.parseFloat(args[4]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[4] + "&c is not a valid " +
                    "number"));
            return true;
        }
        try {
            pitch = Float.parseFloat(args[5]);
        } catch (NumberFormatException e) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&7" + args[5] + "&c is not a valid " +
                    "number"));
            return true;
        }
        Location loc = new Location(world, x, y, z, yaw, pitch);
        saveSpawn(loc, sender);
        return true;
    }

    private void saveSpawn(Location loc, CommandSender sender) {
        plugin.getConfig().set("spawn.world", Objects.requireNonNull(loc.getWorld()).getName());
        plugin.getConfig().set("spawn.x", loc.getX());
        plugin.getConfig().set("spawn.y", loc.getY());
        plugin.getConfig().set("spawn.z", loc.getZ());
        plugin.getConfig().set("spawn.yaw", loc.getYaw());
        plugin.getConfig().set("spawn.pitch", loc.getPitch());
        plugin.saveConfig();
        sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aSpawn was set successfully"));
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.hasPermission("deessentials.setspawn")) {
            return List.of();
        }
        if (sender instanceof Player p) {
            List<String> arg = new ArrayList<>();
            switch (args.length) {
                case 1:
                    for (World world : Bukkit.getWorlds()) {
                        arg.add(world.getName());
                    }
                    break;
                case 2: arg.add(String.valueOf(p.getLocation().getX())); break;
                case 3: arg.add(String.valueOf(p.getLocation().getY())); break;
                case 4: arg.add(String.valueOf(p.getLocation().getZ())); break;
                case 5: arg.add(String.valueOf(p.getLocation().getYaw())); break;
                case 6: arg.add(String.valueOf(p.getLocation().getPitch())); break;
                default: return List.of();
            }
            return arg;
        }
        return List.of();
    }
}
