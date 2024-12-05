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
import com.example.projetoextensao.api.api3.DadosVazaoRio;
import com.example.projetoextensao.api.api3.ApiVazaoService;
import com.example.projetoextensao.api.api3.MockApiVazaoService;
import com.example.projetoextensao.api.RetrofitClient;
import com.example.projetoextensao.chart.CustomLineChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentoTopico3 extends Fragment {

    private CustomLineChart lineChart;
    private Button btn24h, btn7d, btn15d, btn1m;
    private boolean usarMock = true; // Alternar entre API real e dados Mock

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_topico3, container, false);

        // Inicializar elementos da interface
        lineChart = view.findViewById(R.id.lineChartFrag3);
        btn24h = view.findViewById(R.id.btn24h);
        btn7d = view.findViewById(R.id.btn7d);
        btn15d = view.findViewById(R.id.btn15d);
        btn1m = view.findViewById(R.id.btn1m);

        // Configurar eventos dos botões
        btn24h.setOnClickListener(v -> carregarDados("24h"));
        btn7d.setOnClickListener(v -> carregarDados("7d"));
        btn15d.setOnClickListener(v -> carregarDados("15d"));
        btn1m.setOnClickListener(v -> carregarDados("1m"));

        // Carregar dados padrão
        carregarDados("24h");

        return view;
    }

    private void carregarDados(String intervalo) {
        if (usarMock) {
            // Usar dados falsos
            DadosVazaoRio dados = MockApiVazaoService.getDadosMock(intervalo);
            atualizarGrafico(dados.valores, dados.labels);
        } else {
            // Usar API real
            ApiVazaoService apiService = RetrofitClient.getClient("https://sua-api-fragmento3.com/").create(ApiVazaoService.class);
            apiService.getDadosFragmento3(intervalo).enqueue(new Callback<DadosVazaoRio>() {
                @Override
                public void onResponse(Call<DadosVazaoRio> call, Response<DadosVazaoRio> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DadosVazaoRio dados = response.body();
                        atualizarGrafico(dados.valores, dados.labels);
                    } else {
                        Log.e("API", "Erro na resposta: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<DadosVazaoRio> call, Throwable t) {
                    Log.e("API", "Erro ao chamar API: " + t.getMessage());
                }
            });
        }
    }

    private void atualizarGrafico(List<Float> valores, List<String> labels) {
        lineChart.setData(valores, labels);
    }
}
