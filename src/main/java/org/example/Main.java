package org.example;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        System.out.println();
//
//        ClassPrinter cp = new ClassPrinter();
//        try {
//            ClassReader cr = new ClassReader("java.lang.Runnable");
//            cr.accept(cp, 0);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        generateClass();
    }


    private static void generateClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5,ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable",null,"java/lang/Object",
                new String[]{"pkg/Measurable"});

        cw.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "LESS", "I",
                        null, 1)
                .visitEnd();

        cw.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "EQUAL", "I",
                        null, 0)
                .visitEnd();

        cw.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "GREATER", "I",
                        null, 1)
                .visitEnd();

        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        System.out.println(b);
    }
}