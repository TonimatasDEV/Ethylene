import dev.polariscore.tasks.CreateTextFile
import dev.polariscore.tasks.Utils
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

plugins {
    java
    application
}

val minecraftVersion: String by extra
val minestomVersion: String by extra
val logbackVersion: String by extra

group = "dev.polariscore"
version = minecraftVersion + "-" + (System.getenv("VERSION") ?: "dev")
base.archivesName.set("PolarisCore")
sourceSets.main.get().resources.srcDirs("src/main/resources")

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:$minestomVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
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

application {
    mainClass = "dev.polariscore.server.launcher.Launcher"
}

tasks.named<JavaExec>("run") {
    val path = rootDir.toPath().resolve("run")
    
    args = listOf("-nolibraries", "-accepteula")
    workingDir = path.toFile()
    standardInput = System.`in`

    if (!path.exists()) path.createDirectory()
}
