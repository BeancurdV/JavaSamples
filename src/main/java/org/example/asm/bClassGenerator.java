package org.example.asm;

import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Field;

import static org.objectweb.asm.Opcodes.*;

public class bClassGenerator {


    public static void generateAndTest() {
//        byte[] b = generateClass();
//        MyClassLoader myCL = new MyClassLoader();
//        // 通过CLassLoader加载类
//        Class cl = myCL.defineClass("org.example.bean.Comparable", b);

        Class cl = null;
        try {
            cl = new StubClassLoader().findClass("org.example.bean.Comparable");
        }catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(cl.getCanonicalName());

        try {
            Field f = cl.getDeclaredField("GREATER");
            f.setAccessible(true);
            System.out.println("GREATER = " + f.get(null));
        }catch (Exception e) {

        }
    }

    private static byte[] generateClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5,ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "org/example/bean/Comparable",null,"java/lang/Object",
                new String[]{"org/example/bean/Measurable"});

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


    static class StubClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if(name.endsWith("Comparable")) {
                byte[] b = generateClass();
                return defineClass(name, b, 0, b.length);
            }
            return super.findClass(name);
        }
    }
}
