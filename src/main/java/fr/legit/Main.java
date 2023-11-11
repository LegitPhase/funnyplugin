package fr.legit;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this); // i hate bukkit with passion
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
