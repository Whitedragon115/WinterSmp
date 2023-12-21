package winter.winterserver.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import winter.winterserver.event.PlayerAfk;


import java.util.HashSet;
import java.util.Set;

public class SkipNight implements CommandExecutor {
    private final Set<Player> playersWhoClicked = new HashSet<>();
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        boolean PlayerAfk = new PlayerAfk().getCallBeforeMap(player.getName());

        if (cmd.getName().equalsIgnoreCase("SkipNight")) {
            if (!PlayerAfk) {
                player.sendMessage("§7Sorry you can't vote, because you are afk.");
                return true;
            }

            if (isNightTime(player.getWorld().getTime())) {
                if (playersWhoClicked.contains(player)) {
                    player.sendMessage("§7You've already clicked to skip the night!");
                } else {
                    playersWhoClicked.add(player);
                    player.sendMessage("§6You voted to skip the night!");
                    if (playersWhoClicked.size() >= Bukkit.getOnlinePlayers().size()) {
                        //HERE IS AFK PPL AMOUNT
                        for(int i = 5; i >= 1; i--){
                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                sendTitle(onlinePlayer, "§1Skip night in §6" + i, "", 10, 10, 40);
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                           }
                            }
                        }

                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "time set day");
                            playersWhoClicked.clear(); // 清空已點擊的玩家
                    }
            }
        }

        return true;
    }
    // 檢查是否是夜晚時間
    private boolean isNightTime(long time) {
        return time >= 12550 && time <= 23425;
    }
}
