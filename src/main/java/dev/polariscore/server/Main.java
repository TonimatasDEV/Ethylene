package dev.polariscore.server;

import dev.polariscore.server.init.*;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Thread consoleThread;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        MinecraftServer server = MinecraftServer.init();

        MojangAuth.init();
        Levels.init();
        Events.init();
        Commands.init();
        Blocks.init();
        Schedulers.init();

        server.start("0.0.0.0", 25565);
        LOGGER.info("Done ({}s)! For help, type \"help\"",  String.format("%.2f", (double) (System.currentTimeMillis() - startTime) / 1000));
        
        consoleThread = new ConsoleThread();
        consoleThread.start();
    }
    
    public static void stop() {
        consoleThread.interrupt();
        MinecraftServer.stopCleanly();
    }
}
