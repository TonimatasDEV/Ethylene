package net.ethylene.server.launcher;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class ServerEula {
    private final Path file;
    private final boolean agreed;

    public ServerEula(Path path) {
        this.file = path;
        this.agreed = this.readFile();
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    public static boolean checkEula(Path path, boolean accepteula) throws IOException {
        File file = path.toFile();
        ServerEula eula = new ServerEula(path);

        if (!eula.hasAgreedToEULA()) {
            Scanner console = null;
            if (!accepteula) {
                System.out.println("WARNING: It appears you have not agreed to the EULA.\nPlease read the EULA (https://aka.ms/MinecraftEULA) and type 'yes' to continue.");
                System.out.print("Do you accept? (yes/no): ");
                console = new Scanner(System.in);
            }

            while (true) {
                String answer = console != null ? console.nextLine() : "yes";
                if (answer == null || answer.isBlank()) {
                    System.out.println("Please type 'yes' or 'no'.");
                    System.out.print("Do you accept? (yes/no): ");
                    continue;
                }

                switch (answer.toLowerCase()) {
                    case "y", "yes" -> {
                        file.delete();
                        file.createNewFile();
                        eula.save(true);
                        return true;
                    }
                    case "n", "no" -> {
                        System.out.println("You must accept the EULA to continue. Exiting.");
                        return false;
                    }
                    default -> {
                        System.out.println("Please type 'yes' or 'no'.");
                        System.out.print("Do you accept? (yes/no): ");
                    }
                }
            }
        } else {
            return true;
        }
    }

    private boolean readFile() {
        try (InputStream inputstream = Files.newInputStream(this.file)) {
            Properties properties = new Properties();
            properties.load(inputstream);
            return Boolean.parseBoolean(properties.getProperty("eula", "false"));
        } catch (Exception exception) {
            this.save(false);
            return false;
        }
    }

    public boolean hasAgreedToEULA() {
        return this.agreed;
    }

    private void save(boolean agreed) {
        try (OutputStream outputstream = Files.newOutputStream(this.file)) {
            Properties properties = new Properties();
            properties.setProperty("eula", Boolean.toString(agreed));
            properties.store(outputstream, "By changing the setting below to TRUE you are indicating your agreement to our EULA (https://aka.ms/MinecraftEULA).");
        } catch (Exception exception) {
            System.out.println("Could not save " + this.file + ": " + exception.getMessage());
        }
    }
}
