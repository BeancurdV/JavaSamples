package git

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import com.android.build.gradle.internal.cxx.configure.createCxxTasks
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.provider.ValueSupplier.TaskProducer
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import java.io.File

class GitVersionPlugin : Plugin<Project> {
    companion object {
        private const val TAG = "GitVersionPlugin"
    }

    override fun apply(target: Project) {
        log("====================start apply ===========")
        val gitVersionProvider = target.tasks.register<GitVersionTask>("gitVersionProvider",
            GitVersionTask::class.java) {
            gitVersionOutputFile.set(
                File(project.buildDir,
                    "intermediates/gitVersionProvider/output")
            )

            outputs.upToDateWhen { false }
        }




        val androidComponents = target.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant: Variant ->
            val manifestUpdater = target.tasks.register(
                variant.name + "ManifestUpdater",
                ManifestTransformerTask::class.java
            ) {
                gitInfoFile.set(
                    gitVersionProvider.flatMap(GitVersionTask::gitVersionOutputFile)
                )
            }

            variant.artifacts.use(manifestUpdater)
                .wiredWithFiles(
                    ManifestTransformerTask::mergedManifest,
                    ManifestTransformerTask::updatedManifest
                ).toTransform(SingleArtifact.MERGED_MANIFEST)
        }
    }

    private fun log(msg: String) {
        println("[$TAG]: $msg")
    }
}