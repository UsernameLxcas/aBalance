package coins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import utils.Start;

import java.io.File;
import java.io.IOException;

public class coinsH2 {

        public static boolean playerExist(OfflinePlayer player) {
                File playerfile = new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");
                if (playerfile.exists()) {
                        return true;
                } else {
                        return false;
                }
        }

        public static void createplayer(OfflinePlayer player) {
                if(!playerExist(player)) {
                        File playerfile = new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");

                        try {
                                playerfile.createNewFile();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(playerfile);
                        playerconfig.set("User", player.getName());
                        playerconfig.set("Balance", Start.getPlugin().getConfig().getInt("Starter_Coins"));

                        try {
                                playerconfig.save(playerfile);
                        }catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public static File getPlayer(OfflinePlayer player) {
                if (playerExist(player)) {
                        return new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");
                } else {
                        return null;
                }
        }


        public static String getAmount(OfflinePlayer player) {
                File users = getPlayer(player);
                YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(users);
                return playerconfig.getInt("Balance")+"";
        }

        public static void setCoins(OfflinePlayer player, int amount) {
                File users = getPlayer(player);
                YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(users);
                playerconfig.set("Balance", amount);
                try {
                        playerconfig.save(users);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static boolean removeCoins(OfflinePlayer player, int amount) {
                File users = getPlayer(player);
                int coins = Integer.parseInt(getAmount(player));
                int newcoins = coins - amount;

                if(amount>coins) {
                     Player onPlayer = Bukkit.getPlayer(String.valueOf(player));
                     if(onPlayer!=null) {
                             onPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("No_Sufficient_Coins")));
                     }else {
                             Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
                     }
                     return false;
                }else {
                     YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(users);
                     playerconfig.set("Balance", newcoins);
                        try {
                                playerconfig.save(users);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                     return true;
                }

        }










}
