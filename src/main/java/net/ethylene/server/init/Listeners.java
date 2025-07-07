package net.ethylene.server.init;

import net.ethylene.server.listeners.ItemListener;
import net.ethylene.server.listeners.PlayerListeners;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;

public class Listeners {
    public static void init() {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        PlayerListeners.init(globalEventHandler);
        ItemListener.init(globalEventHandler);
    }
}
