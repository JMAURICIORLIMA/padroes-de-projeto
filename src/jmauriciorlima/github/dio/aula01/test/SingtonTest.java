package jmauriciorlima.github.dio.aula01.test;

import jmauriciorlima.github.dio.aula01.domain.Singleton01;
import jmauriciorlima.github.dio.aula01.domain.Singleton02;
import jmauriciorlima.github.dio.aula01.domain.Singleton03;

public class SingtonTest {

    public static void main(String[] args) {

        // Testes relacionados ao Disgner Pattern Singleton
        Singleton01 singoton01 = Singleton01.getInstance();
        System.out.println(singoton01);
        singoton01 = Singleton01.getInstance();
        System.out.println(singoton01);

        System.out.println("        /-------------------------------------/");

        Singleton02 singoton02 = Singleton02.getInstance();
        System.out.println(singoton02);
        singoton02 = Singleton02.getInstance();
        System.out.println(singoton02);

        System.out.println("        /-------------------------------------/");

        Singleton03 singleton03 = Singleton03.getInstance();
        System.out.println(singleton03);
        singleton03 = Singleton03.getInstance();
        System.out.println(singleton03);

    }

}
