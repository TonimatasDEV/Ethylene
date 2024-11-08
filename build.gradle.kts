import dev.polariscore.tasks.CreateTextFile
import dev.polariscore.tasks.Utils

plugins {
    java
}

group = "dev.polariscore"
version = "1.21.2-" + (System.getenv("VERSION") ?: "dev")
base.archivesName.set("PolarisCore")

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:1_21_2-888f9896cc")
    implementation("ch.qos.logback:logback-classic:1.5.7")
}

tasks.register<CreateTextFile>("createLibrariesFile") {
    fileName = "libraries.txt"
    content = Utils.getDependencies(configurations.implementation.get().copy())
}

tasks.register<CreateTextFile>("createRepositoriesFile") {
    fileName = "repositories.txt"
    content = Utils.getRepositories(repositories)
}

tasks.jar {
    from(tasks.getByName("createLibrariesFile"))
    from(tasks.getByName("createRepositoriesFile"))

    manifest {
        attributes(
                "Launcher-Agent-Class" to "dev.polariscore.server.launcher.Agent",
                "Agent-Class" to "dev.polariscore.server.launcher.Agent",
                "Premain-Class" to "dev.polariscore.server.launcher.Agent",
                "Main-Class" to "dev.polariscore.server.launcher.Launcher",
                "Multi-Release" to true
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


