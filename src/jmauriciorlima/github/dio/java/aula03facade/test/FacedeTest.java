package jmauriciorlima.github.dio.java.aula03facade.test;

import jmauriciorlima.github.dio.java.aula03facade.domain.Facade;

public class FacedeTest {

    public static void main(String[] args) {

        Facade facade = new Facade();
        facade.costumerMigration("Allyson", "51234012");

    }
}
