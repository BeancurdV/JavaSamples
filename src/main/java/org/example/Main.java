package org.example;


import org.example.bean.BeancurdV;
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

//        byte[] b = generateClass();
//
//        MyClassLoader myCL = new MyClassLoader();
//        // 通过CLassLoader加载类
//        Class cl = myCL.defineClass("pkg.Comparable", b);
//
//        System.out.println(cl.getCanonicalName());


        BeancurdV bv = new BeancurdV("bv", 32, 1);
        bv.setDirection("Android");
        System.out.println(bv.getName());

    }


    private static byte[] generateClass() {
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
        return b;
    }
}