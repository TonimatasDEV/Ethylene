package dev.photoncore.server.init;

import net.minestom.server.MinecraftServer;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    @Override
    public void run() {
        while (MinecraftServer.isStarted()) {
            Scanner scanner = new Scanner(System.in);
            MinecraftServer.getCommandManager().executeServerCommand(scanner.nextLine());
        }
    }
}
