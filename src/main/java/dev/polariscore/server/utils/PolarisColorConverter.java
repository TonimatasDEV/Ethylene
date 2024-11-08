package dev.polariscore.server.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import java.util.HashMap;
import java.util.Map;

public class PolarisColorConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {
    private static final Map<String, String> colorMap = new HashMap<>();

    static {
        colorMap.put("0", "\u001B[30m");
        colorMap.put("1", "\u001B[34m");
        colorMap.put("2", "\u001B[32m");
        colorMap.put("3", "\u001B[36m");
        colorMap.put("4", "\u001B[31m");
        colorMap.put("5", "\u001B[35m");
        colorMap.put("6", "\u001B[33m");
        colorMap.put("7", "\u001B[37m");
        colorMap.put("8", "\u001B[90m");
        colorMap.put("9", "\u001B[94m");
        colorMap.put("a", "\u001B[92m");
        colorMap.put("b", "\u001B[96m");
        colorMap.put("c", "\u001B[91m");
        colorMap.put("d", "\u001B[95m");
        colorMap.put("e", "\u001B[93m");
        colorMap.put("f", "\u001B[97m");
        colorMap.put("r", "\u001B[0m");
    }
    
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
        String output = in;
        
        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
            output = output.replaceAll("§" + entry.getKey(), entry.getValue());
        }

        return output + "\u001B[0m";
    }
}
