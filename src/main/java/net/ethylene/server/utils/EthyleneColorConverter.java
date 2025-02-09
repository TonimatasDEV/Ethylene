package net.ethylene.server.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class EthyleneColorConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {
    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        return switch (level.toInt()) {
            case 20000 -> "34";
            case 30000 -> "33";
            case 40000 -> "31";
            default -> "39";
        };
    }

    @Override
    protected String transform(ILoggingEvent event, String in) {
        if (in.equals("INFO") || in.equals("WARN") || in.equals("ERROR")) {
            return super.transform(event, in);
        }

        return (in + "§r").replaceAll("§0", "\u001B[30m")
                .replaceAll("§1", "\u001B[34m")
                .replaceAll("§2", "\u001B[32m")
                .replaceAll("§3", "\u001B[36m")
                .replaceAll("§4", "\u001B[31m")
                .replaceAll("§5", "\u001B[35m")
                .replaceAll("§6", "\u001B[33m")
                .replaceAll("§7", "\u001B[37m")
                .replaceAll("§8", "\u001B[90m")
                .replaceAll("§9", "\u001B[94m")
                .replaceAll("§a", "\u001B[92m")
                .replaceAll("§b", "\u001B[96m")
                .replaceAll("§c", "\u001B[91m")
                .replaceAll("§d", "\u001B[95m")
                .replaceAll("§e", "\u001B[93m")
                .replaceAll("§f", "\u001B[97m")
                .replaceAll("§r", "\u001B[0m");
    }
}
