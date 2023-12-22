package opclass

import org.objectweb.asm.*
import org.objectweb.asm.Opcodes.*

/**
 * 打印Class内容
 */
class ClassPrinter(private val targetName: String, nextClassVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM5, nextClassVisitor) {

    companion object {
        const val TAG = "ClassPrinter"
    }


    private var isFind = false

    private var isHasSex = false

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
            // 优先生成Living接口 TODO zfc 1 生成类
//            ClassGenerator().generatorInterface(this.cv)
//            ClassGenerator().generatorInterface("/Users/zhangfengcheng/beancurd/android/dojo/app/build/intermediates/javac/debug/classes/com/beancurdv/techdojo/bean/Living.class")
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

        if(name == "sex"){
            isHasSex = true
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

            // 修改setAge为V2方法
//            if(name == "setAge") {
//                return super.visitMethod(access, name+"_V2", descriptor, signature, exceptions)
//            }
            // 删除getAge方法
            if(name == "getAge") {
                return null
            }
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    override fun visitEnd() {
        if (isFind) {
            println("}")
            if (!isHasSex) {
                // 插入一个 public static String n = "男"
                cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                    "n",
                    "Ljava/lang/String;",
                    null,
                    "a"
                ).visitEnd()


                // 插入一个 public String sex = "男"
                cv.visitField(Opcodes.ACC_PUBLIC,
                    "sex",
                    "Ljava/lang/String;",
                    null,
                    "a"
                ).visitEnd()
                println("insert sex success .....")

                // 增加一个Setter方法
                val mv = cv.visitMethod(
                    Opcodes.ACC_PUBLIC,
                    "getSex",
                    "()Ljava/lang/String;",
                    null,
                    null
                )
                mv.visitCode()
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(
                    GETFIELD,
                    "com/beancurdv/techdojo/bean/Person",
                    "sex",
                    "Ljava/lang/String;"
                )
                mv.visitInsn(IRETURN)
                mv.visitMaxs(1, 1)
                mv.visitEnd()

            }
        }


        super.visitEnd()
    }
}