package com.novur.aliases.command;

import com.novur.aliases.Aliases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Cmd implements CommandExecutor {
    private final Aliases aliases;
    public Cmd(Aliases aliases) {
        this.aliases = aliases;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player)sender;

            if(args.length >= 1) {

                if(args[0].equalsIgnoreCase("reload")) {

                    if(!player.hasPermission("aliases.relod")) {
                        aliases.getUtility().sendNoPermission(player);
                        return true;
                    }

                    aliases.getAliasHandler().reload();
                    aliases.getUtility().send(player,"configReloaded");
                    return true;
                }

                else if(args[0].equalsIgnoreCase("list")) {

                    if(!player.hasPermission("aliases.list")) {
                        aliases.getUtility().sendNoPermission(player);
                        return true;
                    }

                    if(aliases.getAliasHandler().aliasesActive()) {

                        aliases.getAliasHandler().sendList(player);
                        return true;
                    }

                    aliases.getUtility().send(player,"noAliasesRegistered");
                    return true;
                }

                else if(args[0].equalsIgnoreCase("help")) {

                    if(!player.hasPermission("aliases.help")) {
                        aliases.getUtility().sendNoPermission(player);
                        return true;
                    }

                    aliases.getUtility().sendHelp(player);
                    return true;
                }

                else {
                    aliases.getUtility().send(player,"incorrectFormat");
                    return true;
                }
            }

            aliases.getUtility().send(player,"incorrectFormat");
            return true;
        }

        else if(sender instanceof ConsoleCommandSender) {

            if(args.length >= 1) {

                if(args[0].equalsIgnoreCase("reload")) {

                    aliases.getAliasHandler().reload();
                    sender.sendMessage("Aliases config reloaded!");
                    return true;
                }

                else if(args[0].equalsIgnoreCase("list")) {

                    if(aliases.getAliasHandler().aliasesActive()) {

                        aliases.getAliasHandler().sendList(sender);
                        return true;
                    }

                    sender.sendMessage("Error, there are no aliases assigned!");
                    return true;
                }

                else if(args[0].equalsIgnoreCase("help")) {

                    aliases.getUtility().sendHelp(sender);
                    return true;
                }

                else {
                    sender.sendMessage("Incorrect format! /aliases help");
                    return true;
                }
            }

            sender.sendMessage("Incorrect format! /aliases help");
            return true;
        }

        return true;
    }
}
