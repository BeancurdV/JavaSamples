package org.example;


import org.example.asm.aClassParser;
import org.example.asm.bClassGenerator;
import org.example.bean.BeancurdV;
import org.example.bean.Person;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import static org.objectweb.asm.Opcodes.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        System.out.println();

        // 解析Class类
//        aClassParser cp = new aClassParser();
        // SDK中的类
//        try {
//            ClassReader cr = new ClassReader("java.lang.Runnable");
//            cr.accept(cp, 0);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            // 构建CR的方式
//            InputStream ip = Main.class.getClassLoader().getResourceAsStream("org.example.bean.Person".replace('.', '/') + ".class");
////            ClassReader cr1 = new ClassReader(ip);
//            ClassReader cr1 = new ClassReader("org.example.bean.Person");
//            aClassParser cp = new aClassParser();
//            cr1.accept(cp, 0);
//        } catch (Exception e) {
//            Constant.println(e.toString());
//        }



        bClassGenerator.generateAndTest();


//        BeancurdV bv = new BeancurdV("bv", 32, 1);
//        bv.setDirection("Android");
//        System.out.println(bv.getName());
//
//
//        // 哥斯拉和金刚交朋友
//        Person gezzila = new Person("gezzila", 400, 1);
//        Person kingkong = new Person("kingkong", 35, 1);
//        kingkong.makeFriend(gezzila);

    }

}