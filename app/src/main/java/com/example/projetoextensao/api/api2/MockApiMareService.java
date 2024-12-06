package com.example.projetoextensao.api.api2;

import java.util.Arrays;

public class MockApiMareService {

    public static DadosMare getDadosMock(String intervalo) {
        DadosMare dados = new DadosMare();

        switch (intervalo) {
            case "24h":
                dados.valores = Arrays.asList(1.5f, 2.2f, 1.8f, 2.0f, 1.7f);
                dados.labels = Arrays.asList("00h", "06h", "12h", "18h", "24h");
                break;
            case "7d":
                dados.valores = Arrays.asList(1.8f, 1.5f, 2.1f, 1.9f, 2.2f);
                dados.labels = Arrays.asList("00h", "06h", "12h", "18h", "24h");
                break;
            case "15d":
                dados.valores = Arrays.asList(1.7f, 2.3f, 1.8f, 2.0f, 1.5f);
                dados.labels = Arrays.asList("00h", "06h", "12h", "18h", "24h");
                break;
            case "1m":
                dados.valores = Arrays.asList(1.0f, 1.6f, 2.5f, 2.0f, 1.9f);
                dados.labels = Arrays.asList("00h", "06h", "12h", "18h", "24h");
                break;
            default:
                dados.valores = Arrays.asList();
                dados.labels = Arrays.asList();
                break;
        }

        return dados;
    }
}
