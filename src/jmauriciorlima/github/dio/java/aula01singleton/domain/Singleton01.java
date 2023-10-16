package jmauriciorlima.github.dio.java.aula01singleton.domain;

/**
 * Singleton "preguiçoso".
 *
 * @author jmauriciorlima
 */
public class Singleton01 {

    private static Singleton01 instancia;

    private Singleton01() {
        super();
    }

    public static Singleton01 getInstance() {
        if (instancia == null) {
            instancia = new Singleton01();
        }
        return instancia;
    }

}