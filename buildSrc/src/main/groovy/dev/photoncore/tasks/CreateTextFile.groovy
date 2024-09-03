package dev.photoncore.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input

abstract class CreateTextFile extends DefaultTask {
    @Input abstract ListProperty<String> getContent()
    @Input abstract Property<String> getFileName()
    @OutputFile abstract RegularFileProperty getOutputFile()

    CreateTextFile() {
        outputs.upToDateWhen { false }
        outputFile.convention(project.layout.buildDirectory.file(fileName))
    }


    @TaskAction
    void generateFile() {
        def content = content.get().join("\n")
        outputFile.get().asFile.text = content
    }
}
