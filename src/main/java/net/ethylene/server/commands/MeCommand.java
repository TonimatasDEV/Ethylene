package net.ethylene.server.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentStringArray;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class MeCommand extends Command {
    public MeCommand() {
        super("me");
        setDefaultExecutor(this::usage);
        ArgumentStringArray message = ArgumentType.StringArray("message");
        addSyntax(this::execute, message);
    }

    private void usage(CommandSender player, CommandContext arguments) {
        player.sendMessage("Usage: /me <message>");
    }

    private void execute(CommandSender sender, CommandContext arguments) {
        if (sender instanceof Player player) {

            String[] messageParts = arguments.get("message");

            TextComponent.Builder builder = Component.text();

            builder.append(Component.text(" * " + player.getUsername()));

            builder.append(Component.text(" "));
            builder.append(Component.text(messageParts[0]));

            for (int i = 1; i < messageParts.length; i++) {
                builder.append(Component.text(messageParts[i]));
            }

            Component message = builder.build();
            Audiences.all().sendMessage(message);
        }
    }
}

