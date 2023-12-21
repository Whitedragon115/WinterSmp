package winter.winterserver.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import winter.winterserver.Winter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
//test

public class PlayerAfk implements Listener {
    private final Map<Player, Long> lastMoveTimeMap = new HashMap<>();
    private final Map<String, Boolean> CallBefore = new HashMap<>();
    private static final long AFK_TIME_THRESHOLD = 3 * 1000; // 10 seconds

    public boolean getCallBeforeMap(String name) {
        if(CallBefore.get(name)){
            System.out.print("Yes");
            return true;
        }else{
            System.out.print("Yes");
            return false;
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        lastMoveTimeMap.put(player, System.currentTimeMillis());
        CallBefore.putIfAbsent(player.getName(), true);
        if(!CallBefore.get(player.getName())) {
            player.sendMessage("§7You come back :D");
            CallBefore.replace(player.getName(), true);
        }
    }

    // 這是一個示例方法，用於檢查玩家是否 AFK
    public void checkAfkPlayers(Winter plugin) {
        long currentTime = System.currentTimeMillis();

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            Boolean isAfk = Optional.ofNullable(CallBefore.get(player.getName())).orElse(false);

            if (!hasPlayerMoved(player, currentTime) && isAfk) {
                plugin.getServer().broadcastMessage("§7Player " + player.getName() + " is AFK!");
                CallBefore.replace(player.getName(), false);
            }
        }
    }

    private boolean hasPlayerMoved(Player player, long currentTime) {
        long lastMoveTime = lastMoveTimeMap.getOrDefault(player, 0L);
        return currentTime - lastMoveTime < AFK_TIME_THRESHOLD;
        //現在時間 - 上次移動時間 < afk的判定時間
        //沒有移動時間 < afk的判定時間
    }
}