package net.ethylene.server.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.Argument;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.exception.ArgumentSyntaxException;
import net.minestom.server.world.Difficulty;
import org.jetbrains.annotations.NotNull;

public class DifficultyCommand extends Command {
    public DifficultyCommand() {
        super("difficulty");
        setDefaultExecutor(this::usage);
        Argument<?> difficulty = ArgumentType.Word("difficulty").from("peaceful", "easy", "normal", "hard");
        difficulty.setCallback(this::difficultyCallback);
        addSyntax(this::execute, difficulty);
    }

    private void usage(CommandSender player, CommandContext arguments) {
        player.sendMessage("Usage: /difficulty (peaceful|easy|normal|hard)");
    }

    private void execute(CommandSender player, CommandContext arguments) {
        String difficultyName = arguments.get("difficulty");
        Difficulty difficulty = Difficulty.valueOf(difficultyName.toUpperCase());
        MinecraftServer.setDifficulty(difficulty);
        player.sendMessage("You are now playing in " + difficultyName);
    }

    private void difficultyCallback(@NotNull CommandSender sender, @NotNull ArgumentSyntaxException exception) {
        sender.sendMessage("'" + exception.getInput() + "' is not a valid difficulty!");
    }
}

