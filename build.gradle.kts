//file:noinspection VulnerableLibrariesLocal

import dev.photoncore.tasks.Utils
import dev.photoncore.tasks.CreateTextFile

plugins {
    java
}

group = "dev.photoncore.photoncore"
version = "1.0.0"
base.archivesName.set("PhotonCore")
configurations.implementation.get().isCanBeResolved = true

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:8f46913486")
}

tasks.create("createLibrariesFile", CreateTextFile::class) {
    fileName = "libraries.txt"
    //Utils.getDependencies(rootProject.configurations.implementation.get())
    content = Utils.getDependencies(configurations.implementation.get())
}

tasks.create("createRepositoriesFile", CreateTextFile::class) {
    fileName = "repositories.txt"
    content = Utils.getRepositories(repositories)
}

tasks.jar {
    from(tasks.getByName("createLibrariesFile"))
    from(tasks.getByName("createRepositoriesFile"))

    manifest {
        attributes(
                "Launcher-Agent-Class" to "dev.photoncore.server.launcher.Agent",
                "Agent-Class" to "dev.photoncore.server.launcher.Agent",
                "Premain-Class" to "dev.photoncore.server.launcher.Agent",
                "Main-Class" to "dev.photoncore.server.launcher.Launcher",
                "Multi-Release" to true
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


