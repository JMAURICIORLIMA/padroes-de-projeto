package jmauriciorlima.github.dio.aula02strategy.test;

import jmauriciorlima.github.dio.aula02strategy.domain.*;

public class StrategyTest {

    public static void main(String[] args) {

        Strategy01 comportamentoNormal = new ComportamentoNormal();
        Strategy01 comportamentoDefensivo = new ComportamentoDefensivo();
        Strategy01 comportamentoAgressivo = new ComportamentoAgressivo();

        Robo robo = new Robo();

        robo.setComportamento(comportamentoNormal);

        robo.mover();
        robo.mover();

        System.out.println("        /-------------------------------------/");

        robo.setComportamento(comportamentoDefensivo);

        robo.mover();

        System.out.println("        /-------------------------------------/");

        robo.setComportamento(comportamentoAgressivo);

        robo.mover();
        robo.mover();

    }
}
