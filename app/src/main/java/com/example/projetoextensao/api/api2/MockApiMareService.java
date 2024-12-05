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
                dados.valores = Arrays.asList(1.2f, 1.8f, 1.5f, 2.1f, 2.0f, 1.9f, 2.2f);
                dados.labels = Arrays.asList("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb");
                break;
            case "15d":
                dados.valores = Arrays.asList(1.4f, 1.7f, 1.9f, 2.3f, 1.8f, 2.0f, 1.5f);
                dados.labels = Arrays.asList("Dia 1", "Dia 3", "Dia 5", "Dia 7", "Dia 9", "Dia 11", "Dia 13");
                break;
            case "1m":
                dados.valores = Arrays.asList(1.0f, 1.6f, 1.8f, 2.5f, 2.0f, 1.9f, 1.7f);
                dados.labels = Arrays.asList("Sem 1", "Sem 2", "Sem 3", "Sem 4");
                break;
            default:
                dados.valores = Arrays.asList();
                dados.labels = Arrays.asList();
                break;
        }

        return dados;
    }
}
