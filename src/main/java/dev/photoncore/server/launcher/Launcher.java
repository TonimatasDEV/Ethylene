package dev.photoncore.server.launcher;

import dev.photoncore.server.Main;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Logger;

public class Launcher {
    public static void main(String[] args) {
        LibraryInstaller.init();

        try {
            if (!ServerEula.checkEula(Path.of("eula.txt"), Arrays.stream(args).toList().contains("-accepteula"))) return;
        } catch (IOException e) {
            System.out.println("Error on load eula.");
            throw new RuntimeException(e);
        }

        Main.main(args);
    }
}
