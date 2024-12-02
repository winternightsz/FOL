package com.example.projetoextensao.api.api3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api3Service {
    @GET("endpoint3") // Substitua pelo endpoint correto
    Call<Api3Response> getDados();
}
