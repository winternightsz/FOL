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
    private Button botaoSelecionado;
    private boolean usarMock = true; // alterna entre API real e dados Mock

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_principal, container, false);

        // inicializa elementos da interface
        tituloTemperatura = view.findViewById(R.id.tituloTemperatura);
        tempMinima = view.findViewById(R.id.valorTempMinima);
        tempMaxima = view.findViewById(R.id.valorTempMaxima);
        direcaoVento = view.findViewById(R.id.tvDirecaoVento);
        velocidadeVento = view.findViewById(R.id.tvVelocidadeVento);

        btn24h = view.findViewById(R.id.btn24h);
        btn7d = view.findViewById(R.id.btn7d);
        btn15d = view.findViewById(R.id.btn15d);
        btn1m = view.findViewById(R.id.btn1m);

        carregarDados("24h");
        // configura evento de clique para os botoes
        View.OnClickListener listener = v -> {
            // atualiza o estado do botao selecionado
            if (botaoSelecionado != null) {
                botaoSelecionado.setSelected(false); // desmarca o botao anteriormente selecionado
            }

            v.setSelected(true); // marca o botao atual como selecionado
            botaoSelecionado = (Button) v; // atualiza a referencia do botao selecionado

            // chama o metodo para carregar os dados  do botao que foi selecionado
            String intervalo = "";
            if (v == btn24h) {
                intervalo = "24h";
            } else if (v == btn7d) {
                intervalo = "7d";
            } else if (v == btn15d) {
                intervalo = "15d";
            } else if (v == btn1m) {
                intervalo = "1m";
            }

            carregarDados(intervalo);
        };

        // associa o listener aos botoes
        btn24h.setOnClickListener(listener);
        btn7d.setOnClickListener(listener);
        btn15d.setOnClickListener(listener);
        btn1m.setOnClickListener(listener);

        if (savedInstanceState == null) {
            btn24h.setSelected(true); // marca o primeiro botao como selecionado
            botaoSelecionado = btn24h; // atualiza o botao selecionado
        }

        return view;
    }

    private void carregarDados(String intervalo) {
        if (usarMock) {
            //dados do mock
            DadosClimaticos dados = MockApiClimaService.getDadosMock(intervalo);

            // atualiza com os dados
            tituloTemperatura.setText(dados.temperaturaAtual + "°");
            tempMinima.setText(dados.temperaturaMinima + "°");
            tempMaxima.setText(dados.temperaturaMaxima + "°");
            direcaoVento.setText(dados.direcaoVento);
            velocidadeVento.setText(dados.velocidadeVento + " Km/h");
        } else {

            ApiClimaService apiService = RetrofitClient.getClient("https://api.com/").create(ApiClimaService.class);

            apiService.getDadosClimaticos(intervalo).enqueue(new Callback<DadosClimaticos>() {
                @Override
                public void onResponse(Call<DadosClimaticos> call, Response<DadosClimaticos> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DadosClimaticos dados = response.body();

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
