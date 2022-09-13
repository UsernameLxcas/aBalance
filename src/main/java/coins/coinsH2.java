package coins;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import utils.Start;

import java.io.File;
import java.io.IOException;

public class coinsH2 {

        public static boolean playerExist(Player player) {
                File playerfile = new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");
                if (playerfile.exists()) {
                        return true;
                } else {
                        return false;
                }
        }

        public static void createplayer(Player player) {
                if(!playerExist(player)) {
                        File playerfile = new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");

                        try {
                                playerfile.createNewFile();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(playerfile);
                        playerconfig.set("User:", player.getName());
                        playerconfig.set("Balance:", 500);

                        try {
                                playerconfig.save(playerfile);
                        }catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public static File getPlayer(Player player) {
                if (playerExist(player)) {
                        return new File(Start.getPlugin().getDataFolder() + "/users/", player.getUniqueId().toString() + ".yml");
                } else {
                        return null;
                }
        }


        public static String getAmount(Player player) {
                File users = getPlayer(player);
                YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(users);
                return playerconfig.getInt("Balance")+"";
        }






}
