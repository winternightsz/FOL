package com.example.projetoextensao.api.api2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api2Service {
    @GET("endpoint2") // Substitua pelo endpoint correto
    Call<Api2Response> getDados();
}
