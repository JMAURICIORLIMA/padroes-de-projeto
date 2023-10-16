package jmauriciorlima.github.dio.java.aula03facade.domain.subsistema02;

public class ZipCodeApi {

    private static ZipCodeApi instance = new ZipCodeApi();

    private ZipCodeApi() {
        super();
    }

    public static ZipCodeApi getInstance() {
        return instance;
    }

    public String getCity(String zipCode) {
        return "Olinda";
    }
    public String getState(String zipCode) {
        return "PE";
    }

}
