import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import org.gradle.api.Plugin
import org.gradle.api.Project

class LogPlugin : Plugin<Project> {

    companion object {
        private const val TAG = "LogPlugin"
    }

    override fun apply(target: Project) {
        log("====================start apply ===========")
        log("apply target: ${target.displayName}")
        // 注册Transform
        val androidComponentsExtension = target.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponentsExtension.onVariants { variant: Variant ->
            variant.instrumentation.apply {
                transformClassesWith(LogTransform::class.java,InstrumentationScope.PROJECT) {}
                setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_CLASSES)
            }
        }
        log("====================end apply ===========")
    }

    private fun log(msg: String) {
        println("[$TAG]: $msg")
    }
}