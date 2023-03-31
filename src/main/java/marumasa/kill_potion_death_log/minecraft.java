package marumasa.kill_potion_death_log;

import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
