package net.ethylene.server.init;

import net.ethylene.server.commands.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;

public class Commands {
    public static void init() {
        CommandManager commandManager = MinecraftServer.getCommandManager();

        commandManager.register(new GameModeCommand());
        commandManager.register(new DifficultyCommand());
        commandManager.register(new MeCommand());
        commandManager.register(new SaveAllCommand());
        commandManager.register(new StopCommand());

        commandManager.setUnknownCommandCallback((sender, command) -> sender.sendMessage(Component.text("Unknown command", NamedTextColor.RED)));
    }
}
