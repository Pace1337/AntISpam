package dev.pace.antispam.events;

import dev.pace.antispam.EnhancedAntiSpam;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;


public class AntiCommandSpam implements Listener {

    private final EnhancedAntiSpam plugin;

    public AntiCommandSpam(EnhancedAntiSpam plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void checkCommmandSpam(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission("antispam.spam")) {
            return;
        }
        final Long time = System.currentTimeMillis();
        Long lastUse = this.plugin.commandCooldowns.get(player.getName());
        if (lastUse == null) {
            lastUse = 0L;
        }
        if (lastUse + 1500L > time) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("antispam.commandspam")));
            event.setCancelled(true);
        }
        this.plugin.commandCooldowns.remove(player.getName());
        this.plugin.commandCooldowns.put(player.getName(), time);
    }
}
