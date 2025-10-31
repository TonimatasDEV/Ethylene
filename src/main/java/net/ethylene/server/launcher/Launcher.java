package net.ethylene.server.launcher;

import net.ethylene.server.Main;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Launcher {
    static void main(String[] args) {
        if (!Arrays.asList(args).contains("-nolibraries")) {
            LibraryInstaller.init();
        }

        try {
            if (!ServerEula.checkEula(Path.of("eula.txt"), Arrays.asList(args).contains("-accepteula"))) return;
        } catch (IOException e) {
            System.out.println("Error on load eula.");
            throw new RuntimeException(e);
        }

        Main.main();
    }
}
