package org.bukkit.craftbukkit.v1_21_R1.util;

import net.minecraft.server.MinecraftServer;

public class ServerShutdownThread extends Thread {
    private final MinecraftServer server;

    public ServerShutdownThread(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.close();
        } finally {
            try {
                server.reader.getTerminal().restore();
            } catch (Exception e) {
            }
        }
    }
}
