package dev.photoncore.server.init;

import dev.photoncore.server.Main;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.timer.SchedulerManager;
import net.minestom.server.utils.time.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Schedulers {
    private static final Logger LOGGER = LoggerFactory.getLogger(Schedulers.class);
    
    public static void init() {
        SchedulerManager schedulerManager = MinecraftServer.getSchedulerManager();
        
        schedulerManager.buildShutdownTask(() -> {
            Main.stopping = true;

            for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                player.kick("Server closed.");
            }

            LOGGER.info("Saving worlds...");
            MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);
        });
        
        schedulerManager.buildTask(() -> {
            LOGGER.info("Saving all instances...");
            MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);
        }).repeat(5, TimeUnit.MINUTE).schedule();
    }
}
