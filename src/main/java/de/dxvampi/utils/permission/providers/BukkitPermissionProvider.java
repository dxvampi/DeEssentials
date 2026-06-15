package de.dxvampi.utils.permission.providers;

import de.dxvampi.utils.permission.PermissionProvider;
import org.bukkit.entity.Player;

public class BukkitPermissionProvider implements PermissionProvider {
    @Override
    public String getGroup(Player player) {
        if (player.hasPermission("deessentials.group.admin")) return "admin";
        if (player.hasPermission("deessentials.group.vip")) return "vip";
        return "";
    }
}
