package com.example.projetoextensao.fragmentos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetoextensao.R;
import com.example.projetoextensao.api.api2.ApiMareService;
import com.example.projetoextensao.api.api2.DadosMare;
import com.example.projetoextensao.api.api2.MockApiMareService;
import com.example.projetoextensao.api.RetrofitClient;
import com.example.projetoextensao.chart.CustomLineChart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentoTopico2 extends Fragment {

    private CustomLineChart lineChart;
    private Button btn24h, btn7d, btn15d, btn1m;
    private Button botaoSelecionado;
    Button botaoBaixarTabela;
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

        botaoBaixarTabela= view.findViewById(R.id.botaoBaixarTabela);

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

        //pra baixar a tabela
        botaoBaixarTabela.setOnClickListener(v -> {

            botaoBaixarTabela.setSelected(true);
            baixarTabelaDeDados("intervalo_atual", lineChart.getValores(), lineChart.getLabels());

            //depois de 500ms volta
            new android.os.Handler().postDelayed(() -> botaoBaixarTabela.setSelected(false), 500);
        });

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

    private void baixarTabelaDeDados(String intervalo, List<Float> valores, List<String> labels) {
        // cria o documento PDF
        PdfDocument pdfDocument = new PdfDocument();

        //cria uma pagina no PDF
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(14);

        int x = 10;
        int y = 25;

        //titulo do PDF
        canvas.drawText("Dados do Gráfico (" + intervalo + ")", x, y, paint);
        y += 20;

        //cabecalho da tabela
        canvas.drawText("Hora", x, y, paint);
        canvas.drawText("Valor (m)", x + 100, y, paint);
        y += 15;

        paint.setTextSize(12);

        //adiciona os dados do grafico
        for (int i = 0; i < valores.size(); i++) {
            canvas.drawText(labels.get(i), x, y, paint); // Hora
            canvas.drawText(String.format("%.2f", valores.get(i)), x + 100, y, paint); // Valor
            y += 15;
        }

        //finaliza a pagina
        pdfDocument.finishPage(page);

        // salva o PDF no armazenamento local
        File file = new File(requireContext().getExternalFilesDir(null), "dados_grafico_" + intervalo + ".pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(requireContext(), "PDF salvo: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Erro ao salvar PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // encerra o documento
        pdfDocument.close();
    }

}
