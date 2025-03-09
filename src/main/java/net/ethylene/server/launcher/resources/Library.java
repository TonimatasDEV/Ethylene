package net.ethylene.server.launcher.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class Library {
    private final String group, name, version, hash;

    public Library(String library) {
        String[] librarySplit = library.split("\\|");
        String[] libraryInfo = librarySplit[0].split(":");

        this.group = libraryInfo[0];
        this.name = libraryInfo[1];
        this.version = libraryInfo[2];
        this.hash = librarySplit[1];
    }

    public Path getJarDirectory() {
        return Path.of(group.replace(".", "/") + "/" + name + "/" + version);
    }

    public String getJarName() {
        return name + "-" + version + ".jar";
    }

    public Path getDirectory() {
        return Path.of("libraries").resolve(getJarDirectory());
    }

    public Path getPath() {
        return getDirectory().resolve(getJarName());
    }

    public String getLink(String repository) {
        return repository + getJarDirectory().resolve(getJarName()).toString().replaceAll("\\\\", "/");
    }

    public boolean isInstalled() {
        return Files.exists(getPath()) && checkHash();
    }

    private void delete() {
        try {
            Files.delete(getPath());
        } catch (IOException ignored) {
        }
    }

    public boolean checkHash() {
        if (Resources.getHash(getPath().toFile()).equals(hash)) {
            return true;
        } else {
            delete();
            return false;
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void download(String repository) {
        try {
            URL url = URI.create(getLink(repository)).toURL();

            if (!Files.exists(getPath())) {
                getDirectory().toFile().mkdirs();
            }

            Files.copy(url.openStream(), getPath());
        } catch (IOException e) {
            throw new RuntimeException("\nError on download library " + getJarName() + ": " + e);
        }
    }

    public JarFile getJarFile() {
        try {
            return new JarFile(getPath().toFile());
        } catch (IOException e) {
            return null;
        }
    }
}
