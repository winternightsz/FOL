package com.example.projetoextensao.api.api1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api1Service {
    @GET("endpoint1") // Substitua pelo endpoint correto
    Call<Api1Response> getDados();
}
