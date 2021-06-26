package com.novur.aliases.listener;

import com.novur.aliases.Aliases;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Perform implements Listener {
    private final Aliases aliases;
    public Perform(Aliases aliases) {
        this.aliases = aliases;
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event) {
        if(!event.isCancelled()) {
            String message = event.getMessage().replace("/", "");
            if(aliases.getAliasHandler().isRegisteredAlias(message)) {
                String command = aliases.getAliasHandler().getCommand(message);

                if(command.startsWith("-c ")) {
                    String newCmd = command.replace("-c ", "");

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), newCmd.replace("%player%", event.getPlayer().getName()));
                } else {
                    Bukkit.dispatchCommand(event.getPlayer(), command);
                }
                event.setCancelled(true);
            }
        }
    }
}
