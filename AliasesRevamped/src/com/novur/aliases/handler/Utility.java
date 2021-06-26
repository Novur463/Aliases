package com.novur.aliases.handler;

import com.novur.aliases.Aliases;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Utility {
    private final Aliases aliases;
    public Utility(Aliases aliases) {
        this.aliases = aliases;
    }

    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public void send(Player player, String s) {
        player.sendMessage(color(aliases.getConfig().getString(s)));
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(color(aliases.getConfig().getString("noPermission")));
    }

    public void sendHelp(Player player) {
        aliases.getConfig().getStringList("help").forEach(e -> player.sendMessage(color(e)));
    }

    public void sendHelp(CommandSender sender) {
        aliases.getConfig().getStringList("help").forEach(sender::sendMessage);
    }
}
