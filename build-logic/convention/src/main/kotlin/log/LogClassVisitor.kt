package log

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor)
    : ClassVisitor(Opcodes.ASM5, nextClassVisitor) {
    private val className = classContext.currentClassData.className

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val oldMethodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        return LogMethodVisitor(className,oldMethodVisitor,access,name,descriptor)
    }
}