package opclass

import org.objectweb.asm.*

/**
 * 打印Class内容
 */
class ClassPrinter(private val targetName: String) : ClassVisitor(Opcodes.ASM5) {

    companion object {
        const val TAG = "ClassPrinter"
    }

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        if(name?.contains(targetName) == true) {
            println("class $name extends $superName implements ${interfaces.toString().let {
                it.subSequence(1, it.length - 1)
            }} {")
        } else {
            super.visit(version, access, name, signature, superName, interfaces)
        }
    }


    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor? {
        println("   $access $descriptor $name = $value ;")
        return null
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        println("   $access $descriptor $name () throws $exceptions ; ")
        return null
    }

    override fun visitEnd() {
        println("}")
        super.visitEnd()
    }
}