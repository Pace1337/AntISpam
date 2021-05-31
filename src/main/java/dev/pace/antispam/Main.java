package dev.pace.antispam;

import dev.pace.antispam.events.AntiSpam;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static FileConfiguration config;
    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        Main.config = this.getConfig();
        Main.config.options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new AntiSpam(), this);
    }

    @Override
    public void onDisable() {
    }
}
