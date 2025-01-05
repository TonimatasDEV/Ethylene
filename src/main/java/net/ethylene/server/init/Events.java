package net.ethylene.server.init;

import net.ethylene.server.events.ItemEvents;
import net.ethylene.server.events.PlayerEvents;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;

public class Events {
    public static void init() {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        PlayerEvents.init(globalEventHandler);
        ItemEvents.init(globalEventHandler);
    }
}
