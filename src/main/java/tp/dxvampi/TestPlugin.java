package tp.dxvampi;

import org.bukkit.plugin.java.JavaPlugin;
import tp.dxvampi.utils.ConsoleMethods;

public class TestPlugin extends JavaPlugin {

    public final String prefix = "&8[&a&l" + getName() + "&8]";
    private final String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        ConsoleMethods.printInConsole(prefix + " &aTestPlugin has been enabled! &8Version: &f" + version);
    }

    @Override
    public void onDisable() {
        ConsoleMethods.printInConsole(prefix + " &cTestPlugin has been disabled, ciao!");
    }

}
