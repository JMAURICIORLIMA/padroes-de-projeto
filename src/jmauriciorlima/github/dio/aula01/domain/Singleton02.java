package jmauriciorlima.github.dio.aula01.domain;

/**
 * Singleton "apressado"
 *
 * @author jmauriciorlima
 */
public class Singleton02 {

    private static Singleton02 instance = new Singleton02();

    private Singleton02() {
        super();
    }

    public static Singleton02 getInstance() {
        return instance;
    }
}
