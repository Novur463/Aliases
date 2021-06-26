package com.novur.aliases.handler;

import com.novur.aliases.Aliases;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliasHandler {
    private final Aliases aliases;

    int aliasCounter = 0;
    public AliasHandler(Aliases aliases) {
        this.aliases = aliases;
    }

    public void register() {
        for(String key : aliases.getConfig().getConfigurationSection("aliases.").getKeys(false)) {
            ConfigurationSection configurationSection = aliases.getConfig().getConfigurationSection("aliases." + key);

            String linkedCommand = configurationSection.getString("linkedCommand");

            for(String s : configurationSection.getStringList("alias")) {
                aliases.getRegisteredAliases().add(s);

                aliases.getAliasMap().put(s,linkedCommand);
                aliasCounter += 1;
            }
        }
    }

    public void sendList(Player player) {
        for(Map.Entry<String, String> entrySet : aliases.getAliasMap().entrySet()) {
            String alias = entrySet.getKey();
            String linkedCommand = entrySet.getValue();

            player.sendMessage(aliases.getUtility().color(aliases.getConfig().getString("listAliasFormat").replace("%linkedCommand%", linkedCommand).replace("%alias%", alias)));
        }
    }

    public boolean aliasesActive() {
        return aliasCounter >= 1;
    }

    public void sendList(CommandSender sender) {
        for(Map.Entry<String, String> entrySet : aliases.getAliasMap().entrySet()) {
            String alias = entrySet.getKey();
            String linkedCommand = entrySet.getValue();

            sender.sendMessage("Command /" + linkedCommand + " - alias: " + alias);
        }
    }

    public void dump() {
        aliases.getAliasMap().clear();
        aliases.getRegisteredAliases().clear();
    }

    public boolean isRegisteredAlias(String string) {
        return aliases.getRegisteredAliases().contains(string);
    }

    public String getCommand(String alias) {
        return aliases.getAliasMap().get(alias);
    }

    public void reload() {
        aliases.getAliasMap().clear();
        aliases.getRegisteredAliases().clear();

        aliases.reloadConfig();
        register();
    }


}
