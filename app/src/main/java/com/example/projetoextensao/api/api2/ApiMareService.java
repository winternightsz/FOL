package com.example.projetoextensao.api.api2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMareService {
    @GET("mare/dados") // Substitua pelo endpoint correto
    Call<DadosMare> getDadosMare(@Query("intervalo") String intervalo);
}
