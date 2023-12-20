package org.example.asm;

import org.example.Constant;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * 打印一下Class
 */
public class aClassParser extends ClassVisitor {

    public aClassParser() {
        super(ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        Constant.println("name : " + name + " , signature :" + signature);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        Constant.println("filed name : " + name + " , signature :" + signature + " , descriptor : " + descriptor);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        Constant.println("method name : " + name + " , signature :" + signature + " , descriptor : " + descriptor);
        return null;
    }

    @Override
    public void visitEnd() {
        Constant.println("visitEnd");
    }
}
