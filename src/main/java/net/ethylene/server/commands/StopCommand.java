package net.ethylene.server.commands;

import net.ethylene.server.Main;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;

public class StopCommand extends Command {
    public StopCommand() {
        super("stop");
        setCondition(this::condition);
        setDefaultExecutor(this::execute);
    }

    private boolean condition(CommandSender sender, String commandName) {
        return true; // TODO: permissions
    }

    private void execute(CommandSender sender, CommandContext arguments) {
        Main.stop();
    }
}
