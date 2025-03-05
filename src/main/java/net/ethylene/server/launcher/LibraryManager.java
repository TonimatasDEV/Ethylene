package net.ethylene.server.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    public static List<String> getResources(Type type) {
        List<String> resources = new ArrayList<>();

        InputStream inputStream = Launcher.class.getResourceAsStream("/" + type.toString().toLowerCase() + ".txt");

        if (inputStream == null) {
            System.out.println("Error on get " + type.toString().toLowerCase() + ".");
            return resources;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            resources.add(scanner.next());
        }

        scanner.close();
        return resources;
    }
    
    public static boolean checkHash(File file, String hash){
        try {
            return LibraryManager.getSHA256Checksum(file).equals(hash);
        } catch (IOException | NoSuchAlgorithmException exception) {
            return false;
        }
    }

    private static String getSHA256Checksum(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public enum Type {
        LIBRARIES,
        REPOSITORIES
    }
}
