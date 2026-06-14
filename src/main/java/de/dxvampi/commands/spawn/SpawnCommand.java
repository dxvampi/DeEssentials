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

public class SpawnCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public SpawnCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        // 1. Permission
        boolean isPlayer = sender instanceof Player;

        if (!sender.hasPermission("deessentials.spawn")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        // 2. Block console
        if (!isPlayer && args.length < 1) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can only use this command" +
                    " targeted to players");
            return true;
        }

        // 3. Check if spawn exists in config.yml
        if (!plugin.getConfig().contains("spawn")) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cSpawn has not been configured on " +
                    "the server yet."));
            return true;
        }

        // 4. Read config
        String worldName = plugin.getConfig().getString("spawn.world", "worldst");
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");


        // 5. Check if the world even exists
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cError: The spawn world does not exist anymore."));
            return true;
        }
        Location spawnLoc = new Location(world, x, y, z, yaw, pitch);

        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                CommandErrors.RaiseUnknownPlayer(plugin, sender);
                return true;
            }
            if (!isPlayer) {
                teleport(target, spawnLoc);
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aSuccessfully teleported &f" +
                        target.getName() + "&a to the spawn"));
                return true;
            }
            if (sender != target) {
                if (sender.hasPermission("deessentials.spawn.others")) {
                    teleport(target, spawnLoc);
                    sender.sendMessage(MessageUtils.getColoredMessage(plugin.getPrefix() + "&aSuccessfully teleported &f" +
                            target.getName() + "&a to the spawn"));
                    return true;
                } else {
                    CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args, "You can only use the " +
                            "command on yourself");
                    return true;
                }
            } else {
                teleport(target, spawnLoc);
                return true;
            }
        }

        teleport((Player) sender, spawnLoc);
        return true;

    }

    private void teleport(Player player, Location spawn) {
        player.teleport(spawn);
        player.sendMessage(MessageUtils.getColoredMessage("&aYou have been teleported to spawn!"));
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.hasPermission("deessentials.spawn")) {
            return List.of();
        }

        if (args.length == 1) {
            List<String> players = new ArrayList<>();

            if (!sender.hasPermission("deessentials.spawn.others")) {
                players.add(sender.getName());
                return players;
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                players.add(p.getName());
            }
            return players;
        }

        return List.of();
    }
}
