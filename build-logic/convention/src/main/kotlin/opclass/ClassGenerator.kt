package opclass

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.*
import java.io.File

/**
 * 生成一个类
 */
class ClassGenerator {


    /**
     * 生成一个接口，格式如下
     *  interface Living {
     *      val GREATER:Int = 1
     *      fun animal():boolean
     *  }
     */
    fun generatorInterface(nextClassVisitor: ClassVisitor) {

        printVisitorChain(nextClassVisitor)

        /**
         * cw.visit(V1_5,ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
        "org/example/bean/Comparable",null,"java/lang/Object",
        new String[]{"org/example/bean/Measurable"});
         */
        nextClassVisitor.visit(
            V1_8, ACC_INTERFACE + ACC_INTERFACE + ACC_ABSTRACT,
            "com/beancurdv/techdojo/bean/Living", null, "java/lang/Object", null
        )
        // 插入方法
        nextClassVisitor.visitMethod(
            ACC_PUBLIC + ACC_ABSTRACT,
            "animal",
            "()Z",
            null,
            null
        ).visitEnd()
        nextClassVisitor.visitField(
            ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "GREATER", "I",
            null, 1
        ).visitEnd()
        nextClassVisitor.visitEnd()

        println("generate living interface success")
    }

    private fun printVisitorChain(next: ClassVisitor?) {
        println("visitor chain ================start===================")
        var cur: ClassVisitor? = next
        while (cur != null) {
            println(cur)
            // protected ClassVisitor cv;
            val f = ClassVisitor::class.java.getDeclaredField("cv")
            f.isAccessible = true
            cur = f.get(cur) as? ClassVisitor?
        }
        println("visitor chain ================end===================")
    }

    /**
     * 生成一个接口，格式如下
     *  interface Living {
     *      val GREATER:Int = 1
     *      fun animal():boolean
     *  }
     */
    fun generatorInterface(filePath: String) {
        val cw = ClassWriter(Opcodes.ASM9)
        /**
         * cw.visit(V1_5,ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
        "org/example/bean/Comparable",null,"java/lang/Object",
        new String[]{"org/example/bean/Measurable"});
         */
        cw.visit(
            V1_8, ACC_INTERFACE + ACC_INTERFACE + ACC_ABSTRACT,
            "com/beancurdv/techdojo/bean/Living", null, "java/lang/Object", null
        )
        // 插入方法
        cw.visitMethod(
            ACC_PUBLIC + ACC_ABSTRACT,
            "animal",
            "()Z",
            null,
            null
        ).visitEnd()
        cw.visitField(
            ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "GREATER", "I",
            null, 1
        ).visitEnd()
        cw.visitEnd()

        // 写入文件
        File(filePath).apply {
            deleteOnExit()
            writeBytes(cw.toByteArray())

            println("file : $absoluteFile")
        }
    }
}