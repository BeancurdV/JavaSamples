import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import opclass.ClassPrinter
import org.objectweb.asm.ClassVisitor

abstract class LogTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {

    override fun isInstrumentable(classData: ClassData): Boolean {
        return true
    }

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
//        return LogClassVisitor(classContext, nextClassVisitor)
        return ClassPrinter("Person",nextClassVisitor)
    }
}