package de.dxvampi.utils.permission;

import org.bukkit.entity.Player;

public interface PermissionProvider {
    String getGroup(Player player);
}
