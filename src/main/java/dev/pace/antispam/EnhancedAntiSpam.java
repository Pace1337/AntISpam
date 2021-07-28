package dev.pace.antispam;

import dev.pace.antispam.commands.EnhancedAntiSpamCommand;
import dev.pace.antispam.events.AntiCommandSpam;
import dev.pace.antispam.events.AntiSpam;
import dev.pace.antispam.updates.Metrics;
import dev.pace.antispam.updates.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public final class EnhancedAntiSpam extends JavaPlugin {

    public static FileConfiguration config;
    public static EnhancedAntiSpam instance = null;
    public HashMap<String, Long> commandCooldowns = new HashMap<>();

    public static EnhancedAntiSpam getInstance() {
        return instance;
    }

    public String getVersion() {
        return this.getDescription().getVersion();
    }

    public void reloadConfiguration() {
        this.reloadConfig();
        config = this.getConfig();
    }

    @Override
    public void onEnable() {
        EnhancedAntiSpam.instance = this;
        EnhancedAntiSpam.config = this.getConfig();
        EnhancedAntiSpam.config.options().copyDefaults(true);
        config.addDefault("update-checker", true);
        this.saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new AntiSpam(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiCommandSpam(this), this);
        getCommand("antispam").setExecutor(new EnhancedAntiSpamCommand()); // Aliases: EnhancedAntiSpam
        new Metrics(this, 12246);

        final Logger logger = this.getLogger();
        new UpdateChecker(this, 92645).getVersion(version -> {
            if (!config.getBoolean("update-checker")) return;
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("No updates found for Enhanced AntiSpam!");
            } else {
                logger.info("There is a new update available for Enhanced AntiSpam. Download it here: https://www.spigotmc.org/resources/enhanced-antispam.92645/");
            }
        });
    }

    @Override
    public void onDisable() {
    }
}
