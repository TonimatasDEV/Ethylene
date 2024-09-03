package dev.photoncore.server.launcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class LibraryInstaller {
    private static final String filledChar = "=";
    private static final String voidChar = " ";
    private static final int numberChars = 10;
    
    public static void init() {
        int max = LibraryManager.getResources(LibraryManager.Type.LIBRARIES).size();
        int part = max / numberChars;
        long time = System.currentTimeMillis();
        List<JarFile> jarFiles = new ArrayList<>();

        int now = 0;
        boolean update = true;
        for (String repository : LibraryManager.getResources(LibraryManager.Type.REPOSITORIES)) {
            for (String library : LibraryManager.getResources(LibraryManager.Type.LIBRARIES)) {
                
                String[] strings = library.split(":");
                String jarPath = strings[0].replace(".", "/") + "/" + strings[1] + "/" + strings[2];
                String jarName = strings[1] + "-" + strings[2] + ".jar";

                if (update) {
                    int filledParts = now / part;
                    String result = "Downloading libraries: [" + filledChar.repeat(filledParts) + voidChar.repeat(numberChars - filledParts) + "]";
                    printReplace(result, library);
                    update = false;
                }
                
                if (!new File("libraries/" + jarPath + "/" + jarName).exists()) downloadLibrary(repository + jarPath, jarPath, jarName);

                JarFile jarFile = null;
                try {
                    jarFile = new JarFile("libraries/" + jarPath + "/" + jarName);
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
    }

    public static void printReplace(String newMessage, String otherMessage) {
        System.out.print("\r" + newMessage + " " + otherMessage);
    }

    private static void downloadLibrary(String url, String jarDirectory, String jarName) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URI(url + "/" + jarName).toURL().openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                new File("libraries/" + jarDirectory).mkdirs();
                FileOutputStream outputStream = new FileOutputStream("libraries/" + jarDirectory + "/" + jarName);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
                //LOGGER.info("Library downloaded: " + jarName);
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error on download library: " + jarName);
        }
    }
}
