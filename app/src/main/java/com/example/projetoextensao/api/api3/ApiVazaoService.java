package com.example.projetoextensao.api.api3;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiVazaoService {
    @GET("fragmento3/dados") // Substitua pelo endpoint correto
    Call<DadosVazaoRio> getDadosFragmento3(@Query("intervalo") String intervalo);
}
