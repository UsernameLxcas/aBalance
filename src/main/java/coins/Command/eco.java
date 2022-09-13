package coins.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import utils.Start;
import coins.coins;

import java.sql.Connection;

public class eco implements CommandExecutor {

    Connection connection = Start.getPlugin().getSQL();
    public String getUsage() {
        return ChatColor.translateAlternateColorCodes('&', "&cIncorrect Usage!\n" +
                "&e/eco give <player> <amount> &b- use this command to give coins to a player!\n" +
                "&e/eco remove <player> <amount> &b- use this command to remove coins from a player\n" +
                "&e/eco get <player> &b- use this command to get a player amount coins\n" +
                "&e/eco reset <player> &b- use this command to reset the balance of a player\n");
    }

    public String getStorage() {
        return Start.getPlugin().getDatabase().getString("Storage");
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender.hasPermission("anybalance.admin")) {
            if(args.length==0) {
                sender.sendMessage(getUsage());
            }else {
                switch (args[0]) {
                    case "give":
                        if(args.length!=3) {
                            sender.sendMessage(getUsage());
                        }else {
                            Player player = Bukkit.getPlayer(args[1]);
                            int amount = Integer.parseInt(args[2]);
                            if(getStorage().equalsIgnoreCase("None")) {
                                //code
                            }else if(getStorage().equalsIgnoreCase("MySQL")) {
                                int oldcoins = coins.getCoins(connection, player.getName());
                                coins.setCoins(connection, player.getName(), oldcoins+amount);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Added_Coins").replace("%second_player%", player.getName()).replace("%second_playercoins%", amount+"")));

                            }
                        }
                        break;
                    case "remove":
                        if(args.length!=3) {
                            sender.sendMessage(getUsage());
                        }else {
                            Player player = Bukkit.getPlayer(args[1]);
                            int amount = Integer.parseInt(args[2]);
                            if (getStorage().equalsIgnoreCase("None")) {
                                //code
                            } else if (getStorage().equalsIgnoreCase("MySQL")) {
                                coins.removeCoins(connection, player.getName(),amount);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Removed_Coins").replace("%second_player%", player.getName()).replace("%second_playercoins%", amount+"")));

                            }
                        }
                        break;
                    case "get":
                        if(args.length!=2) {
                            sender.sendMessage(getUsage());
                        }else {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (getStorage().equalsIgnoreCase("None")) {
                                //code
                            } else if (getStorage().equalsIgnoreCase("MySQL")) {
                                int coinsz = coins.getCoins(connection, player.getName());
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Coins_Other").replace("%second_player%", player.getName()).replace("%second_playercoins%", coinsz+"")));
                            }
                        }
                        break;
                    case "reset":
                        if(args.length!=2) {
                            sender.sendMessage(getUsage());
                        }else {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (getStorage().equalsIgnoreCase("None")) {
                                //code
                            } else if (getStorage().equalsIgnoreCase("MySQL")) {
                                coins.setCoins(connection, player.getName(), Start.getPlugin().getConfig().getInt("Starter_Coins"));
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Reset_Coins").replace("%second_player%", player.getName()).replace("%default_coins%", Start.getPlugin().getConfig().getInt("Starter_Coins")+"")));
                            }
                        }
                        break;
                    default:
                        sender.sendMessage(getUsage());
                        break;
                }
            }
        }

        return true;
    }
}
