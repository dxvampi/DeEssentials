package de.dxvampi.listeners;

import de.dxvampi.DeEssentials;
import de.dxvampi.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {

    private final DeEssentials plugin;

    public PlayerListener(DeEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.toLowerCase().contains("aternos")) {
            event.setCancelled(true);
            player.sendMessage(MessageUtils.getColoredMessage("&c&lYou can't swear!"));
        }

    }

}
