package dev.photoncore.server;

import net.minestom.server.MinecraftServer;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        AnsiConsole.systemInstall();
        MinecraftServer server = MinecraftServer.init();
        
        server.start("0.0.0.0", 25565);
        LOGGER.info("Done! ({}s)",  String.format("%.2f", (double) (System.currentTimeMillis() - startTime) / 1000));
    }
}
