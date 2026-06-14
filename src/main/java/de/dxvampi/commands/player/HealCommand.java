package de.dxvampi.commands.player;

import de.dxvampi.DeEssentials;
import de.dxvampi.commands.base.CommandErrors;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HealCommand implements CommandExecutor, TabCompleter {

    private final DeEssentials plugin;

    public HealCommand(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        // Base permission
        if (!sender.hasPermission("deessentials.heal")) {
            CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args);
            return true;
        }

        final boolean isPlayer = sender instanceof Player;

        // Console check if no player is targeted
        if (args.length == 0 && !isPlayer) {
            CommandErrors.RaiseConsoleCommandAvailability(plugin, sender, label, args, "You can only use it if targeting a player");
            return true;
        }

        // Self-heal
        if (args.length == 0) {
            Player player;

            player = (Player) sender;

            healPlayer(player);

            player.sendMessage(MessageUtils.getColoredMessage("&aYou have been healed!"));
            return true;
        }

        // This block is if a player is targeted or args.length >= 1

        // Player to heal
        Player target = Bukkit.getPlayer(args[0]);

        //Check if the player exists or is online
        if (target == null) {
            CommandErrors.RaiseUnknownPlayer(plugin, sender);
            return true;
        }
        // If the player lacks "others" permission but has self-heal available
        if (target != sender) {
            if (!sender.hasPermission("deessentials.heal.others")) {
                CommandErrors.RaiseInsufficientPermission(plugin, sender, label, args, "You can only heal yourself");
                return true;
            }
        }

            // If player can do shit
            healPlayer(target);

            target.sendMessage(MessageUtils.getColoredMessage("&aYou have been healed!"));

            // Does not send the message twice
            if (target != sender) {
                sender.sendMessage(MessageUtils.getColoredMessage("&aYou have successfully healed &f" + target.getDisplayName() + "&a!"));
            }

            return true;
        }

    private void healPlayer(Player player) {
        double currentMaxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();

        player.setHealth(currentMaxHealth);
        player.setAbsorptionAmount(0.0);
        player.setFoodLevel(20);
        player.setFireTicks(0);
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if (args.length > 1) {
            return List.of();
        }

        List<String> playerList = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                playerList.add(p.getName());
            }
        }

        return playerList;
    }
}



