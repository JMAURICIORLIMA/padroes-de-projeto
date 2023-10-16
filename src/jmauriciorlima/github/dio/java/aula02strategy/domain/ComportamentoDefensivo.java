package jmauriciorlima.github.dio.java.aula02strategy.domain;

public class ComportamentoDefensivo implements Strategy01 {
    @Override
    public void mover() {
        System.out.println("Movendo-se defensivamente...");
    }
}
