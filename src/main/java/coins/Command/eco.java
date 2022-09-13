package coins.Command;

import coins.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import utils.Start;


import java.sql.Connection;

import static coins.Command.ecoGet.checkUser;

public class eco implements CommandExecutor {

    Connection connection = Start.getPlugin().getSQL();

    public String getStorage() {
        return Start.getPlugin().getDatabase().getString("Storage");

    }

    public String getStorageMethod() {
        return ChatColor.translateAlternateColorCodes('&', "&eAnyBalance is currently running with:\n" +
                "&b"+Start.getPlugin().getDatabase().getString("Storage"));

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender.hasPermission("anybalance.admin")) {
            if(args.length==0) {
                sender.sendMessage(Messages.getUsage());
            }else {
                switch (args[0]) {
                    case "give":
                        if(args.length!=3) {
                            sender.sendMessage(Messages.getUsage());
                        }else {
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                            if(getStorage().equalsIgnoreCase("MySQL")) {
                                ecoGive.checkUser(connection, player, sender, args);
                            }else if(getStorage().equalsIgnoreCase("None")) {
                                ecoGive.checkUser(null, player, sender, args);
                            }
                        }
                        break;
                    case "remove":
                        if(args.length!=3) {
                            sender.sendMessage(Messages.getUsage());
                        }else {
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                            if(getStorage().equalsIgnoreCase("MySQL")) {
                                ecoRemove.checkUser(connection, player, sender, args);
                            }else if(getStorage().equalsIgnoreCase("None")) {
                                ecoRemove.checkUser(null, player, sender, args);
                            }

                        }
                        break;
                    case "get":
                        if(args.length!=2) {
                            sender.sendMessage(Messages.getUsage());
                        }else {
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                                if(getStorage().equalsIgnoreCase("MySQL")) {
                                    ecoGet.checkUser(connection, player, sender, args);
                                }else if(getStorage().equalsIgnoreCase("None")) {
                                    ecoGet.checkUser(null, player, sender, args);
                            }

                        }
                        break;
                    case "reset":
                        if(args.length!=2) {
                            sender.sendMessage(Messages.getUsage());
                        }else {
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                            if (getStorage().equalsIgnoreCase("MySQL")) {
                                ecoReset.checkUser(connection, player, sender, args);
                            } else if (getStorage().equalsIgnoreCase("None")) {
                                ecoReset.checkUser(null, player, sender, args);
                            }
                        }
                        break;
                    case "storage":
                        sender.sendMessage(getStorageMethod());
                        break;
                    case "reload":
                        Start.getPlugin().reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("reload")));
                        break;
                    default:
                        sender.sendMessage(Messages.getUsage());
                        break;
                }
            }
        }

        return true;
    }
}
