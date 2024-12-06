package com.example.projetoextensao.fragmentos;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetoextensao.R;
import com.example.projetoextensao.api.api2.ApiMareService;
import com.example.projetoextensao.api.api2.DadosMare;
import com.example.projetoextensao.api.api2.MockApiMareService;
import com.example.projetoextensao.api.RetrofitClient;
import com.example.projetoextensao.chart.CustomLineChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentoTopico2 extends Fragment {

    private CustomLineChart lineChart;
    private Button btn24h, btn7d, btn15d, btn1m;
    private Button botaoSelecionado;
    private boolean usarMock = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_topico2, container, false);


        lineChart = view.findViewById(R.id.lineChartFrag2);
        btn24h = view.findViewById(R.id.btn24h);
        btn7d = view.findViewById(R.id.btn7d);
        btn15d = view.findViewById(R.id.btn15d);
        btn1m = view.findViewById(R.id.btn1m);


        carregarDados("24h");

        View.OnClickListener listener = v -> {
            if (botaoSelecionado != null) {
                botaoSelecionado.setSelected(false);
            }

            v.setSelected(true);
            botaoSelecionado = (Button) v;

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
            // usa dados mock
            DadosMare dados = MockApiMareService.getDadosMock(intervalo);
            atualizarGrafico(dados.valores, dados.labels);
        } else {
            // usa API real
            ApiMareService apiService = RetrofitClient.getClient("https://api-mare.com/").create(ApiMareService.class);
            apiService.getDadosMare(intervalo).enqueue(new Callback<DadosMare>() {
                @Override
                public void onResponse(Call<DadosMare> call, Response<DadosMare> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DadosMare dados = response.body();
                        atualizarGrafico(dados.valores, dados.labels);
                    } else {
                        Log.e("API", "Erro na resposta: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<DadosMare> call, Throwable t) {
                    Log.e("API", "Erro ao chamar API: " + t.getMessage());
                }
            });
        }
    }

    private void atualizarGrafico(List<Float> valores, List<String> labels) {
        lineChart.setData(valores, labels);
    }
}
