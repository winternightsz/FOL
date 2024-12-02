package com.example.projetoextensao.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ChartTemporario extends View {

    private Paint paint;
    private float[] valores = {100, 200, 150, 250, 180};
    private String[] labels = {"Jan", "Fev", "Mar", "Abr", "Mai"};

    //como o construtor ta na classe View
    //public View(android.content.Context context, @android.annotation.Nullable android.util.AttributeSet attrs) { /* compiled code */ }
    public ChartTemporario(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int largura = getWidth();
        int altura = getHeight();
        int espacamento = 20;

        int larguraBarra = (largura - (espacamento * (valores.length + 1))) / valores.length;

        //desenho das barras
        for (int i = 0; i < valores.length; i++) {
            int xInicio = espacamento + i * (larguraBarra + espacamento);
            int xFim = xInicio + larguraBarra;
            float yFim = altura - valores[i];

            //aqui desenha
            canvas.drawRect(xInicio, yFim, xFim, altura, paint);

            //colocar as labels
            paint.setTextSize(30);
            canvas.drawText(labels[i], xInicio, altura - 10, paint);
        }
    }

    public void atualizarDados(float[] novosValores, String[] novosLabels) {
        if (novosValores != null && novosLabels != null && novosValores.length == novosLabels.length) {
            this.valores = novosValores;
            this.labels = novosLabels;
            invalidate(); // Redesenha o gráfico com os novos dados
        }
    }




}

