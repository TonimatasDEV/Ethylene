package dev.photoncore.server;

import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        LOGGER.info("Hello World!");
    }
}
