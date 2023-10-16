package jmauriciorlima.github.dio.java.aula03facade.domain;

import jmauriciorlima.github.dio.java.aula03facade.domain.subsistema01.CrmService;
import jmauriciorlima.github.dio.java.aula03facade.domain.subsistema02.ZipCodeApi;

public class Facade {

    public void costumerMigration(String name, String zipCode) {
        String city = ZipCodeApi.getInstance().getCity(zipCode);
        String state = ZipCodeApi.getInstance().getState(zipCode);

        CrmService.saveClient(name, zipCode, city, state);
    }
}
