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
    private boolean usarMock = true; // Alternar entre API real e dados Mock

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_topico2, container, false);

        // Inicializar elementos da interface
        lineChart = view.findViewById(R.id.lineChartFrag2);
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
            DadosMare dados = MockApiMareService.getDadosMock(intervalo);
            atualizarGrafico(dados.valores, dados.labels);
        } else {
            // Usar API real
            ApiMareService apiService = RetrofitClient.getClient("https://sua-api-mare.com/").create(ApiMareService.class);
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
