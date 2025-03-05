package net.ethylene.server.launcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Stream;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LibraryInstaller {
    private static final String filledChar = "=";
    private static final String voidChar = " ";
    private static final int numberChars = 20;

    public static void init() {
        int max = LibraryManager.getResources(LibraryManager.Type.LIBRARIES).size();
        int part = max / numberChars;
        long time = System.currentTimeMillis();
        List<JarFile> jarFiles = new ArrayList<>();
        List<Path> installedPaths = new ArrayList<>();

        int now = 0;
        boolean update = true;
        for (String repository : LibraryManager.getResources(LibraryManager.Type.REPOSITORIES)) {
            for (String library : LibraryManager.getResources(LibraryManager.Type.LIBRARIES)) {
                String[] libraryString = library.split("\\|");
                String[] libraryInfo = libraryString[0].split(":");
                String libraryHash = libraryString[1];

                String jarPath = libraryInfo[0].replace(".", "/") + "/" + libraryInfo[1] + "/" + libraryInfo[2];
                String jarName = libraryInfo[1] + "-" + libraryInfo[2] + ".jar";

                if (update) {
                    int filledParts = now / part;

                    String result;
                    if (numberChars >= filledParts) {
                        result = "Downloading libraries: [" + filledChar.repeat(filledParts) + voidChar.repeat(numberChars - filledParts) + "] " + getPercentage(now, max);
                    } else {
                        result = "Downloading libraries: [" + filledChar.repeat(numberChars) + "] 100%";
                    }

                    printReplace(result);
                    update = false;
                }

                Path path = Path.of("libraries/" + jarPath + "/" + jarName);
                installedPaths.addAll(addPreviousFolders(path));

                File libraryFile = path.toFile();
                boolean installed = false;
                
                if (libraryFile.exists()) {
                    if (LibraryManager.checkHash(libraryFile, libraryHash)) {
                        installed = true;
                    } else {
                        libraryFile.delete();
                    }
                }
                
                if (!installed) downloadLibrary(repository + jarPath, jarPath, jarName, libraryHash);

                JarFile jarFile = null;

                try {
                    jarFile = new JarFile(path.toFile());
                } catch (IOException ignored) {
                }

                if (jarFiles.contains(jarFile) || jarFile == null) continue;

                jarFiles.add(jarFile);
                try {
                    Agent.appendJarFile(jarFile);
                } catch (IOException e) {
                    System.out.println("Error on load libraries.");
                    throw new RuntimeException(e);
                }

                update = true;
                now++;
            }
        }


        System.out.println();
        System.out.println("All libraries downloaded/loaded successfully in " + (System.currentTimeMillis() - time) + "ms");

        deleteOtherFiles(installedPaths);
    }

    public static void printReplace(String newMessage) {
        System.out.print("\r" + newMessage);
    }

    private static void downloadLibrary(String url, String jarDirectory, String jarName, String hash) {
        new File("libraries/" + jarDirectory).mkdirs();
        
        File jarFile = new File("libraries/" + jarDirectory + "/" + jarName);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URI(url + "/" + jarName).toURL().openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(jarFile);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
                
                if (!LibraryManager.checkHash(jarFile, hash)) {
                    throw new RuntimeException("Error on download library: " + jarName);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error on download library: " + jarName, e);
        }
    }

    private static String getPercentage(int now, int max) {
        return (int) ((double) now / max * 100) + "%";
    }

    private static void deleteOtherFiles(List<Path> installedPaths) {
        try {
            Stream<Path> paths = Files.walk(Path.of("libraries"));

            for (Path path : paths.toList()) {
                if (!installedPaths.contains(path) && Files.exists(path)) {
                    if (Files.isDirectory(path)) {
                        deleteDirectoryIfExists(path);
                    }

                    Files.deleteIfExists(path);
                }
            }

            paths.close();
        } catch (IOException e) {
            System.out.println("Error deleting a file not installed by the core in the libraries folder. " + e.getMessage());
        }
    }

    private static void deleteDirectoryIfExists(Path path) {
        File[] files = path.toFile().listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                deleteDirectoryIfExists(file.toPath());
            } else {
                file.delete();
            }
        }
    }

    private static List<Path> addPreviousFolders(Path path) {
        List<Path> paths = new ArrayList<>();
        Path previous = path.getParent();

        if (previous != null) {
            paths.addAll(addPreviousFolders(previous));
        }

        paths.add(path);

        return paths;
    }
}
