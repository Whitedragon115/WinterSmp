package winter.winterserver;

import org.bukkit.Bukkit;
import winter.winterserver.command.SkipNight;
import winter.winterserver.command.Location;
import winter.winterserver.event.PlayerAfk;
import winter.winterserver.event.PlayerChat;
import winter.winterserver.event.Playerjoin;
import org.bukkit.plugin.java.JavaPlugin;
import winter.winterserver.event.SleepSkip;


import java.util.Objects;

public final class Winter extends JavaPlugin {

    @Override
    public void onEnable() {
        Location lccmd = new Location();
        SkipNight fecmd = new SkipNight();
        PlayerAfk playerAfk = new PlayerAfk();

        getServer().getPluginManager().registerEvents(new Playerjoin(), this);
        getServer().getPluginManager().registerEvents(new SleepSkip(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(playerAfk, this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> playerAfk.checkAfkPlayers(this), 0L, 20L);

        Objects.requireNonNull(getCommand("MyLocation")).setExecutor(lccmd);
        Objects.requireNonNull(getCommand("LocationToChat")).setExecutor(lccmd);
        Objects.requireNonNull(getCommand("NeitherLocation")).setExecutor(lccmd);
        Objects.requireNonNull(getCommand("LastDeadLocation")).setExecutor(lccmd);
        Objects.requireNonNull(getCommand("OverWorldLocation")).setExecutor(lccmd);
        Objects.requireNonNull(getCommand("SkipNight")).setExecutor(fecmd);


        getServer().getConsoleSender().sendMessage("Plugin Loaded");

    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage("Plugin Unloaded");
    }


}
