package dev.pace.antispam;

import dev.pace.antispam.events.AntiSpam;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new AntiSpam(), this);
    }

    @Override
    public void onDisable() {
    }
}
