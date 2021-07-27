package dev.pace.antispam.commands;

import dev.pace.antispam.EnhancedAntiSpam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnhancedAntiSpamCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        final Player p = (Player) sender;
        if (label.equalsIgnoreCase("antispam")) {
            if (args.length == 0) {
                if (!p.hasPermission("antispam.command")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("commands.nopermission1")));
                } else {
                    p.sendMessage(this.cc("&6Running&b Enhanced AntiSpam " + EnhancedAntiSpam.getInstance().getVersion()));
                    p.sendMessage(this.cc("&fAll useful commands:"));
                    p.sendMessage(this.cc("&e/antispam - &fAntiSpam main command."));
                    p.sendMessage(this.cc("&e/antispam reload - &fReload AntiSpam's plugin configuration."));
                    p.sendMessage(this.cc("&e/antispam clearchat - &fClears the chat."));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission("antispam.admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("commands.nopermission2")));
                    return true;
                }
                try {
                    EnhancedAntiSpam.getInstance().reloadConfiguration();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("commands.reloadcomplete")));
                } catch (NullPointerException e) {
                    sender.sendMessage(ChatColor.DARK_RED + "Error! Check console for more details.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("clearchat")) {
                if (!p.hasPermission("antispam.clearchat")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("commands.nopermission3")));
                    return true;
                }
                if (args.length == 1) {
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', EnhancedAntiSpam.config.getString("commands.clearchat")));
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String cc(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}