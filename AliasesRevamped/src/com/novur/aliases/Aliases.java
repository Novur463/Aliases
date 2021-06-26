package com.novur.aliases;

import com.novur.aliases.command.Cmd;
import com.novur.aliases.handler.AliasHandler;
import com.novur.aliases.handler.Utility;
import com.novur.aliases.listener.Perform;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aliases extends JavaPlugin {
    private Aliases instance;
    private Map<String, String> aliasMap;
    private List<String> registeredAliases;
    private AliasHandler aliasHandler;
    private Utility utility;

    @Override
    public void onEnable() {
        instance = this;
        utility = new Utility(instance);

        aliasMap = new HashMap<>();
        registeredAliases = new ArrayList<>();

        getCommand("aliases").setExecutor(new Cmd(instance));
        getServer().getPluginManager().registerEvents(new Perform(instance), this);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        aliasHandler = new AliasHandler(instance);
        aliasHandler.register();
    }

    @Override
    public void onDisable() {
        aliasHandler.dump();
    }

    public Utility getUtility() {
        return utility;
    }

    public AliasHandler getAliasHandler() {
        return aliasHandler;
    }

    public List<String> getRegisteredAliases() {
        return registeredAliases;
    }

    public Map<String, String> getAliasMap() {
        return aliasMap;
    }
}
