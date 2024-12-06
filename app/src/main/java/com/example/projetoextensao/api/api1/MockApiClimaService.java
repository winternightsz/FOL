package com.example.projetoextensao.api.api1;

import java.util.Random;

public class MockApiClimaService {

    public static DadosClimaticos getDadosMock(String intervalo) {
        DadosClimaticos dados = new DadosClimaticos();

        Random random = new Random();

        if (intervalo.equals("24h")) {
            dados.temperaturaAtual = String.valueOf(18 + random.nextInt(5)); //Vai gerar um valor aleatorio de 18 a 22
            dados.temperaturaMinima = String.valueOf(12 + random.nextInt(3));
            dados.temperaturaMaxima = String.valueOf(22 + random.nextInt(3));
            dados.direcaoVento = "Sudeste";
            dados.velocidadeVento = String.valueOf(10 + random.nextInt(5));  //Vai gerar um valor aleatorio de 10 a 14
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
