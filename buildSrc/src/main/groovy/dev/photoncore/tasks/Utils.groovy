package dev.photoncore.tasks

import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.dsl.RepositoryHandler

class Utils {
    static List<String> getDependencies(Configuration configuration) {
        List<String> libraries = new ArrayList<>()

        configuration.resolvedConfiguration.resolvedArtifacts.each { artifact ->
            String library = "${artifact.moduleVersion.id.group}:${artifact.moduleVersion.id.name}:${artifact.moduleVersion.id.version}"
            libraries.add(library)
        }

        return libraries
    }

    static List<String> getRepositories(RepositoryHandler handler) {
        List<String> repositories = new ArrayList<>()

        handler.each { repository ->
            repositories.add(repository.url.toString())
        }

        return repositories
    }
}
