package git

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class ManifestTransformerTask : DefaultTask() {
    @get:InputFile
    abstract val gitInfoFile: RegularFileProperty

    @get:InputFile
    abstract val mergedManifest: RegularFileProperty

    @get:OutputFile
    abstract val updatedManifest: RegularFileProperty

    @TaskAction
    fun taskAction() {
        val gitVersion = gitInfoFile.get().asFile.readText()
        var manifest = mergedManifest.get().asFile.readText()

        manifest = manifest.replace(
            "android:versionCode=\"1\"",
            "android:versionCode=\"${gitVersion}\""
        )

        updatedManifest.get().asFile.writeText(manifest)
    }
}