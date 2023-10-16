package jmauriciorlima.github.dio.java.aula02strategy.domain;

public class Robo {

    private Strategy01 comportamento;

    public void setComportamento(Strategy01 comportamento) {
        this.comportamento = comportamento;
    }

    public void mover() {
        comportamento.mover();
    }
}
