package net.ethylene.server.launcher;

import net.ethylene.server.launcher.resources.Library;
import net.ethylene.server.launcher.resources.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class LibraryInstaller {
    private static final String filledChar = "=";
    private static final String voidChar = " ";
    private static final int numberChars = 20;

    public static void init() {
        int max = Resources.getLibraries().size();
        int part = max / numberChars;
        long time = System.currentTimeMillis();

        int now = 0;
        for (String repository : Resources.getRepositories()) {
            for (Library library : Resources.getLibraries()) {
                int filledParts = now / part;

                String result;
                if (numberChars >= filledParts) {
                    result = "Downloading libraries: [" + filledChar.repeat(filledParts) + voidChar.repeat(numberChars - filledParts) + "] " + getPercentage(now, max);
                } else {
                    result = "Downloading libraries: [" + filledChar.repeat(numberChars) + "] 100%";
                }

                System.out.print("\r" + result);

                if (!library.isInstalled()) {
                    library.download(repository);
                }

                Agent.appendJarFile(library.getJarFile());

                now++;
            }
        }

        System.out.println();
        System.out.println("All libraries downloaded/loaded successfully in " + (System.currentTimeMillis() - time) + "ms");

        deleteOtherFiles();
    }

    private static String getPercentage(int now, int max) {
        return (int) ((double) now / max * 100) + "%";
    }

    private static void deleteOtherFiles() {
        List<Path> libraries = Resources.getLibraries().stream().map(Library::getPath).toList();

        try {
            Stream<Path> paths = Files.walk(Path.of("libraries"));

            for (Path path : paths.toList()) {
                if (!Files.exists(path)) return;

                if (libraries.stream().noneMatch(path1 -> path1.startsWith(path))) {
                    if (Files.isDirectory(path)) {
                        deleteDirectory(path);
                    } else {
                        Files.delete(path);
                    }
                }
            }

            paths.close();
        } catch (IOException e) {
            System.out.println("Error deleting a file not installed by the core in the libraries folder. " + e.getMessage());
        }
    }

    private static void deleteDirectory(Path folder) throws IOException {
        File[] files = folder.toFile().listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                deleteDirectory(file.toPath());
            } else {
                Files.delete(file.toPath());
            }
        }

        Files.delete(folder);
    }
}
