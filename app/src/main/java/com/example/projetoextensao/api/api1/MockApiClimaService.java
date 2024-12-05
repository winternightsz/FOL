package com.example.projetoextensao.api.api1;

import java.util.Random;

public class MockApiClimaService {

    public static DadosClimaticos getDadosMock(String intervalo) {
        DadosClimaticos dados = new DadosClimaticos();

        Random random = new Random();

        // Simulando dados baseados no intervalo
        if (intervalo.equals("24h")) {
            dados.temperaturaAtual = String.valueOf(18 + random.nextInt(5)); // 18°C a 22°C
            dados.temperaturaMinima = String.valueOf(12 + random.nextInt(3)); // 12°C a 14°C
            dados.temperaturaMaxima = String.valueOf(22 + random.nextInt(3)); // 22°C a 24°C
            dados.direcaoVento = "Sudeste";
            dados.velocidadeVento = String.valueOf(10 + random.nextInt(5)); // 10 Km/h a 14 Km/h
        } else if (intervalo.equals("7d")) {
            dados.temperaturaAtual = String.valueOf(20 + random.nextInt(5));
            dados.temperaturaMinima = String.valueOf(15 + random.nextInt(3));
            dados.temperaturaMaxima = String.valueOf(25 + random.nextInt(3));
            dados.direcaoVento = "Nordeste";
            dados.velocidadeVento = String.valueOf(12 + random.nextInt(5));
        } else if (intervalo.equals("15d")) {
            dados.temperaturaAtual = String.valueOf(22 + random.nextInt(5));
            dados.temperaturaMinima = String.valueOf(16 + random.nextInt(3));
            dados.temperaturaMaxima = String.valueOf(26 + random.nextInt(3));
            dados.direcaoVento = "Norte";
            dados.velocidadeVento = String.valueOf(15 + random.nextInt(5));
        } else if (intervalo.equals("1m")) {
            dados.temperaturaAtual = String.valueOf(25 + random.nextInt(5));
            dados.temperaturaMinima = String.valueOf(20 + random.nextInt(3));
            dados.temperaturaMaxima = String.valueOf(30 + random.nextInt(3));
            dados.direcaoVento = "Oeste";
            dados.velocidadeVento = String.valueOf(18 + random.nextInt(5));
        }

        return dados;
    }
}
