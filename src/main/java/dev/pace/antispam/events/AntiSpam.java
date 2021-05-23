package dev.pace.antispam.events;

import dev.pace.antispam.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class AntiSpam implements Listener {

    private HashMap<Player, String> last = new HashMap<>(); // Anti repeat message.
    private ArrayList<Player> antispam = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        if (!last.containsKey(p)) {
            last.put(p, msg);
        } else {
            if (msg.equalsIgnoreCase(last.get(p))) {
                e.setCancelled(true);
                p.sendMessage("§7[§4§lAntiSpam§7]§7 Please do not repeat your messages.");
                return;
            }
        }
        if (!antispam.contains(p)) {
            antispam.add(p);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

                @Override
                public void run() {
                    if (antispam.contains(p)) antispam.remove(p);
                }
            }, 40L);
        } else {
            e.setCancelled(true);
            p.sendMessage("§7[§4§lAntiSpam§7]§7 Please do not spam.");
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
            e.setCancelled(true);
            p.sendMessage("§7[§4§lAntiSpam§7]§7 Please refrain from using that much caps lock!");
        }
    }
}
