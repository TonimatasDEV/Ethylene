package dev.photoncore.server;

import dev.photoncore.server.init.Commands;
import dev.photoncore.server.init.Events;
import dev.photoncore.server.init.Levels;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.Instance;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        AnsiConsole.systemInstall();
        MinecraftServer server = MinecraftServer.init();

        MojangAuth.init();
        Levels.init();
        Events.init();
        Commands.init();

        server.start("0.0.0.0", 25565);
        LOGGER.info("Done! ({}s)",  String.format("%.2f", (double) (System.currentTimeMillis() - startTime) / 1000));
    }
    
    public static void stop() {
        MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);

        for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            player.kick("Server closed.");
        }
        
        MinecraftServer.stopCleanly();
        System.exit(0);
    }
}
