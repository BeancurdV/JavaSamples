package opclass

import org.objectweb.asm.*

/**
 * 打印Class内容
 */
class ClassPrinter(private val targetName: String, nextClassVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM5, nextClassVisitor) {

    companion object {
        const val TAG = "ClassPrinter"
    }


    private var isFind = false

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        if (name?.contains(targetName) == true) {
            isFind = true
            println(
                "class $name extends $superName implements ${
                    interfaces.toString().let {
                        it.subSequence(1, it.length - 1)
                    }
                } {"
            )
        } else {
            isFind = false
        }
        super.visit(version, access, name, signature, superName, interfaces)
    }


    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor? {
        if (isFind) {
            println("   $access $descriptor $name = $value ;")
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        if (isFind) {
            println("   $access $descriptor $name () throws $exceptions ; ")
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    override fun visitEnd() {
        if (isFind) {
            println("}")
        }
        super.visitEnd()
    }
}