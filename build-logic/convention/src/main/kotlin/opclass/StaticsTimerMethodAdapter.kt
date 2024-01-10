package opclass

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*


/*
 public void setAge(int age) {
    long i = System.currentTimeMillis();
    this.age = age;
    i = System.currentTimeMillis() - i;
    Log.e("BV_Time", "setAge : " + i + " ms");
}

// access flags 0x1
public setAge(I)V
L0
LINENUMBER 23 L0
INVOKESTATIC java/lang/System.currentTimeMillis ()J
LSTORE 2
L1
LINENUMBER 24 L1
ALOAD 0
ILOAD 1
PUTFIELD com/beancurdv/techdojo/bean/Person.age : I
        L2
LINENUMBER 25 L2
INVOKESTATIC java/lang/System.currentTimeMillis ()J
LLOAD 2
LSUB
LSTORE 2
L3
LINENUMBER 26 L3
LDC "BV_Time"
NEW java/lang/StringBuilder
        DUP
INVOKESPECIAL java/lang/StringBuilder.<init> ()V
LDC "setAge : "
INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
LLOAD 2
INVOKEVIRTUAL java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
LDC " ms"
INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
INVOKESTATIC android/util/Log.e (Ljava/lang/String;Ljava/lang/String;)I
        POP
L4
LINENUMBER 27 L4
        RETURN
L5
LOCALVARIABLE this Lcom/beancurdv/techdojo/bean/Person; L0 L5 0
LOCALVARIABLE age I L0 L5 1
LOCALVARIABLE i J L1 L5 2
MAXSTACK = 4
MAXLOCALS = 4
}
 */
class StaticsTimerMethodAdapter(
    next: MethodVisitor,
    private val startIndex: Int
) : MethodVisitor(ASM8, next) {

    override fun visitCode() {
        super.visitCode()
//        INVOKESTATIC java/lang/System.currentTimeMillis ()J
//        LSTORE 2
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        visitIntInsn(LSTORE, startIndex)
    }

    /**
     *         L2
    LINENUMBER 25 L2
    INVOKESTATIC java/lang/System.currentTimeMillis ()J
    LLOAD 2
    LSUB
    LSTORE 2
    L3
    LINENUMBER 26 L3
    LDC "BV_Time"
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    LDC "setAge : "
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    LLOAD 2
    INVOKEVIRTUAL java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
    LDC " ms"
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    INVOKESTATIC android/util/Log.e (Ljava/lang/String;Ljava/lang/String;)I
    POP
    L4
    LINENUMBER 27 L4
    RETURN
    L5
    LOCALVARIABLE this Lcom/beancurdv/techdojo/bean/Person; L0 L5 0
    LOCALVARIABLE age I L0 L5 1
    LOCALVARIABLE i J L1 L5 2
    MAXSTACK = 4
    MAXLOCALS = 4
     */
    override fun visitInsn(opcode: Int) {
        if (opcode in IRETURN..RETURN || opcode == ATHROW) {
            // INVOKESTATIC java/lang/System.currentTimeMillis ()J
            // LLOAD 2
            // LSUB
            // LSTORE 2
            visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
            visitVarInsn(LLOAD,startIndex)
            visitInsn(LSUB)
            visitVarInsn(LSTORE,startIndex)


//            LINENUMBER 26 L3
//            LDC "BV_Time"
//            NEW java/lang/StringBuilder
//                    DUP
//            INVOKESPECIAL java/lang/StringBuilder.<init> ()V
//                    LDC "setAge : "
//            INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//            LLOAD 2
//            INVOKEVIRTUAL java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
//            LDC " ms"
//            INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//            INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
//            INVOKESTATIC android/util/Log.e (Ljava/lang/String;Ljava/lang/String;)I
//            POP

            visitLdcInsn("BV_Time")
            visitTypeInsn(NEW,"java/lang/StringBuilder")
            visitInsn(DUP)
            visitMethodInsn(
                INVOKESPECIAL,
                "java/lang/StringBuilder",
                "<init>",
                "()V",
                false
            )

            visitLdcInsn("setName : ")
            visitMethodInsn(
                INVOKEVIRTUAL,
                "java/lang/StringBuilder",
                "append",
                "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
                false
            )

            visitVarInsn(LLOAD, startIndex)
            visitMethodInsn(
                INVOKEVIRTUAL,
                "java/lang/StringBuilder",
                "append",
                "(J)Ljava/lang/StringBuilder;",
                false
            )

            visitLdcInsn(" ms")
            visitMethodInsn(
                INVOKEVIRTUAL,
                "java/lang/StringBuilder",
                "append",
                "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
                false
            )

            visitMethodInsn(
                INVOKEVIRTUAL,
                "java/lang/StringBuilder",
                "toString",
                "()Ljava/lang/String;",
                false
            )
            visitMethodInsn(
                INVOKESTATIC,
                "android/util/Log",
                "e",
                "(Ljava/lang/String;Ljava/lang/String;)I",
                false
            )
            visitInsn(POP)
        }
        super.visitInsn(opcode)
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        super.visitMaxs(maxStack + startIndex, maxLocals + startIndex)
    }
}