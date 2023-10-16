package jmauriciorlima.github.dio.java.aula01singleton.domain;

/**
 * Singleton "Lazy Holder".
 *
 * @author jmauriciorlima
 * @see <a href="https://stackoverflow.com/a/24018148">Refencia</a>
 */
public class Singleton03 {

    private static class InstanceHolder {
        public static Singleton03 instancia = new Singleton03();

    }

    private Singleton03() {
        super();
    }

    public static Singleton03 getInstance() {
        return InstanceHolder.instancia;
    }

}