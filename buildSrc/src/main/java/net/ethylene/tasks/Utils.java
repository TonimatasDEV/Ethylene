package net.ethylene.tasks;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ResolvedArtifact;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.ArtifactRepository;
import org.gradle.api.artifacts.repositories.UrlArtifactRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {
    public static List<String> getDependencies(Configuration configuration) throws IOException, NoSuchAlgorithmException {
        List<String> libraries = new ArrayList<>();
        Set<ResolvedArtifact> artifacts = configuration.getResolvedConfiguration().getResolvedArtifacts();

        for (ResolvedArtifact artifact : artifacts) {
            String hash = getSHA256Checksum(artifact.getFile());
            
            String library = artifact.getModuleVersion().getId().getGroup() + ":" +
                    artifact.getModuleVersion().getId().getName() + ":" +
                    artifact.getModuleVersion().getId().getVersion() + "|" + hash;
            libraries.add(library);
        }

        return libraries;
    }

    public static List<String> getRepositories(RepositoryHandler handler) {
        List<String> repositories = new ArrayList<>();

        for (ArtifactRepository repository : handler) {
            if (repository instanceof UrlArtifactRepository urlArtifactRepository) {
                repositories.add(urlArtifactRepository.getUrl().toString());
            }
        }

        return repositories;
    }

    public static String getSHA256Checksum(File file) throws IOException, NoSuchAlgorithmException {
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
}

