package net.ethylene.server.launcher.resources;

import net.ethylene.server.launcher.Launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Scanner;

public class Resources {
    public static List<Library> getLibraries() {
        return getResources("libraries").stream().map(Library::new).toList();
    }

    public static List<String> getRepositories() {
        return getResources("repositories");
    }

    private static List<String> getResources(String type) {
        List<String> resources = new ArrayList<>();

        InputStream inputStream = Launcher.class.getResourceAsStream("/" + type + ".txt");

        if (inputStream == null) {
            System.out.println("Error on get " + type + ".");
            return resources;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            resources.add(scanner.next());
        }

        scanner.close();
        return resources;
    }

    public static String getHash(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            return HexFormat.of().formatHex(digest.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
