package com.example.projetoextensao;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetoextensao.api.api1.ApiClimaService;
import com.example.projetoextensao.api.api1.DadosClimaticos;
import com.example.projetoextensao.api.RetrofitClient;
import com.example.projetoextensao.api.api1.MockApiClimaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentoPrincipal extends Fragment {

    private TextView tituloTemperatura, tempMinima, tempMaxima, direcaoVento, velocidadeVento;
    private Button btn24h, btn7d, btn15d, btn1m;
    private boolean usarMock = true; // Alternar entre API real e Mock


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_principal, container, false);

        // Inicializar elementos da interface
        tituloTemperatura = view.findViewById(R.id.tituloTemperatura);
        tempMinima = view.findViewById(R.id.valorTempMinima);
        tempMaxima = view.findViewById(R.id.valorTempMaxima);
        direcaoVento = view.findViewById(R.id.tvDirecaoVento);
        velocidadeVento = view.findViewById(R.id.tvVelocidadeVento);

        btn24h = view.findViewById(R.id.btn24h);
        btn7d = view.findViewById(R.id.btn7d);
        btn15d = view.findViewById(R.id.btn15d);
        btn1m = view.findViewById(R.id.btn1m);

        // Configurar eventos dos botões
        btn24h.setOnClickListener(v -> carregarDados("24h"));
        btn7d.setOnClickListener(v -> carregarDados("7d"));
        btn15d.setOnClickListener(v -> carregarDados("15d"));
        btn1m.setOnClickListener(v -> carregarDados("1m"));

        return view;
    }

    private void carregarDados(String intervalo) {
        if (usarMock) {
            // Simulacao de dados
            DadosClimaticos dados = MockApiClimaService.getDadosMock(intervalo);

            // Atualizar a interface com os dados falsos
            tituloTemperatura.setText(dados.temperaturaAtual + "°");
            tempMinima.setText(dados.temperaturaMinima + "°");
            tempMaxima.setText(dados.temperaturaMaxima + "°");
            direcaoVento.setText(dados.direcaoVento);
            velocidadeVento.setText(dados.velocidadeVento + " Km/h");
        } else {
            // API Real
            ApiClimaService apiService = RetrofitClient.getClient("https://api.com/").create(ApiClimaService.class);

            apiService.getDadosClimaticos(intervalo).enqueue(new Callback<DadosClimaticos>() {
                @Override
                public void onResponse(Call<DadosClimaticos> call, Response<DadosClimaticos> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DadosClimaticos dados = response.body();

                        // Atualizar a interface com os dados reais
                        tituloTemperatura.setText(dados.temperaturaAtual + "°");
                        tempMinima.setText(dados.temperaturaMinima + "°");
                        tempMaxima.setText(dados.temperaturaMaxima + "°");
                        direcaoVento.setText(dados.direcaoVento);
                        velocidadeVento.setText(dados.velocidadeVento + " Km/h");
                    } else {
                        Log.e("API", "Erro na resposta: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<DadosClimaticos> call, Throwable t) {
                    Log.e("API", "Erro ao chamar a API: " + t.getMessage());
                }
            });
        }
    }

}
