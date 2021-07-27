package dev.pace.antispam.events;

import dev.pace.antispam.EnhancedAntiSpam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.HashMap;
import java.util.UUID;

public class AntiSpam implements Listener {

    private HashMap<Player, String> last = new HashMap<>(); // Anti repeat message.
    private HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        if (p.isOp())
            return;
        if (p.hasPermission("antispam.bypass"))
            return;
        if (!last.containsKey(p)) {
            last.put(p, msg);
        } else {
            if (msg.equalsIgnoreCase(last.get(p))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("antispam.repeat")));
                return;
            }
        }

        if (!cooldown.containsKey(p.getUniqueId())) {
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());
            return;
        }

        if (System.currentTimeMillis() - cooldown.get(p.getUniqueId()) > 3000) {
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());
        } else {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("antispam.spam")));
            return;
        }

        String[] array = msg.split("");
        int actions = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(array[i].toUpperCase())) {
                actions++;
            }
        }
        if (actions >= 20) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("antispam.caps")));
        }
    }
}