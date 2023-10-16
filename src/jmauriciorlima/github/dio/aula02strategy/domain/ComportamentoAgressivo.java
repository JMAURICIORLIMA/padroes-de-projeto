package jmauriciorlima.github.dio.aula02strategy.domain;

public class ComportamentoAgressivo implements Strategy01 {
    @Override
    public void mover() {
        System.out.println("Movendo-se agressivamente...");
    }
}
