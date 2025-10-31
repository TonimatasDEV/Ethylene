package net.ethylene.server;

import net.ethylene.server.init.*;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import org.everbuild.blocksandstuff.blocks.BlockBehaviorRuleRegistrations;
import org.everbuild.blocksandstuff.blocks.BlockPlacementRuleRegistrations;
import org.everbuild.blocksandstuff.blocks.PlacedHandlerRegistration;
import org.everbuild.blocksandstuff.fluids.MinestomFluids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Thread consoleThread;

    public static void main() {
        long startTime = System.currentTimeMillis();

        MinecraftServer server = MinecraftServer.init(new Auth.Online());

        // Libraries - Start
        // BlockAndStuff
        BlockPlacementRuleRegistrations.registerDefault();
        BlockBehaviorRuleRegistrations.registerDefault();
        PlacedHandlerRegistration.registerDefault();
        MinestomFluids.enableFluids();
        MinestomFluids.enableVanillaFluids();
        // Libraries - End

        Levels.init();
        Listeners.init();
        Commands.init();
        Schedulers.init();

        server.start("0.0.0.0", 25565);
        LOGGER.info("Done ({}s)! For help, type \"help\"", String.format("%.2f", (double) (System.currentTimeMillis() - startTime) / 1000));

        consoleThread = new ConsoleThread();
        consoleThread.start();
    }

    public static void stop() {
        consoleThread.interrupt();
        MinecraftServer.stopCleanly();
    }
}
