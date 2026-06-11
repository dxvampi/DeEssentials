package tp.dxvampi.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConsoleMethods {
    public static void printInConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
