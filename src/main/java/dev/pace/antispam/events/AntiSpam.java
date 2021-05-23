package dev.pace.antispam.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AntiSpam implements Listener {

    private HashMap<Player, String> last = new HashMap<>(); // Anti repeat message.
    private ArrayList<Player> antispam = new ArrayList<>();
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
                p.sendMessage("§7[§b§lAntiSpam§7]§7 Please do not repeat your messages.");
                // This way people are not allowed to repeat their messages, like bro, we already saw your message. :AndyIsCool:
                return;
            }
        }

        if (!cooldown.containsKey(p.getUniqueId())) {
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());
        }

        if (System.currentTimeMillis() - cooldown.get(p.getUniqueId()) > 2000) {
            // Chat is allowed, update cooldown again. :thumb:
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());
        } else {
            // Cancel monkeys from spamming. :thumb:
            e.setCancelled(true);
            p.sendMessage("§7[§b§lAntiSpam§7]§7 You are not allowed to spam. Wait 2 seconds until sending another message.");
            return;
        }

        String[] array = msg.split(" ");
        int actions = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(array[i].toUpperCase())) {
                actions++;
            }
        }
        if (actions >= 3) {
            // Cancel capslock :salamalaka:
            e.setCancelled(true);
            p.sendMessage("§7[§b§lAntiSpam§7]§7 Please do not use that much capslock.");
        }
    }
}
