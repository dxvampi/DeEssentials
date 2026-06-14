package de.dxvampi.utils.permission.providers;

import de.dxvampi.utils.permission.PermissionProvider;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPermissionProvider implements PermissionProvider {
    private Permission vaultPerms;

    public VaultPermissionProvider() {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServicesManager().getRegistration(Permission.class);
        if (rsp != null) {
            this.vaultPerms = rsp.getProvider();
        }
    }

    @Override
    public String getGroup(Player player) {
        if (vaultPerms != null && vaultPerms.hasGroupSupport()) {
            return vaultPerms.getPrimaryGroup(player);
        }
        return "default";
    }
}
