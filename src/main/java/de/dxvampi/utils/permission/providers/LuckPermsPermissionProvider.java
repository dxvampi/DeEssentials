package de.dxvampi.utils.permission.providers;

import de.dxvampi.utils.permission.PermissionProvider;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsPermissionProvider implements PermissionProvider {
    private LuckPerms luckPerms;

    public LuckPermsPermissionProvider() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            this.luckPerms = provider.getProvider();
        }
    }
    @Override
    public String getGroup(Player player) {
        if (luckPerms == null) return "default";

        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            return user.getPrimaryGroup();
        }
        return "default";
    }
}
