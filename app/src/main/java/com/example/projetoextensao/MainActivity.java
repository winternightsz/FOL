package com.example.projetoextensao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.projetoextensao.fragmentos.FragmentoTopico1;
import com.example.projetoextensao.fragmentos.FragmentoTopico2;
import com.example.projetoextensao.fragmentos.FragmentoTopico3;

public class MainActivity extends AppCompatActivity {

    private Button botaoSelecionado;
    private Button botaoTopico1, botaoTopico2, botaoTopico3;
    private ImageView logoUni, logoOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaoTopico1 = findViewById(R.id.botaoTopico1);
        botaoTopico2 = findViewById(R.id.botaoTopico2);
        botaoTopico3 = findViewById(R.id.botaoTopico3);

        logoUni = findViewById(R.id.logoUnivali);
        logoOut = findViewById(R.id.logoOutro);

        // esconder botoes inicialmente
        setBotaoVisibilidade(false);

        // configura listener pra troca de fragmentos e atualizacao do estado do botao
        View.OnClickListener listener = view -> {
            //troca o estado do botao que antes tava selecionado
            if (botaoSelecionado != null) {
                botaoSelecionado.setSelected(false);
            }

            // marca o botao clicado como selecionado
            view.setSelected(true);
            botaoSelecionado = (Button) view; // atualiza o botao selecionado

            // troca para o fragmento escolhido
            if (view.getId() == R.id.botaoTopico1) {
                trocarFragmento(new FragmentoPrincipal());
            } else if (view.getId() == R.id.botaoTopico2) {
                trocarFragmento(new FragmentoTopico2());
            } else if (view.getId() == R.id.botaoTopico3) {
                trocarFragmento(new FragmentoTopico3());
            }
        };

        // configura listeners nos botoes
        botaoTopico1.setOnClickListener(listener);
        botaoTopico2.setOnClickListener(listener);
        botaoTopico3.setOnClickListener(listener);

        // mostra o FragmentoInicial
        if (savedInstanceState == null) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            trocarFragmento(new FragmentoInicial());
            botaoTopico1.setSelected(true); // marca o primeiro botao como selecionado
            botaoSelecionado = botaoTopico1; // atualiza o botao selecionado
        }


        // defini icones e texto da barra de status como escuro
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //metodo para trocar fragmentos
    public void trocarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void trocarFragmentoSemVoltar(Fragment fragmento) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmento)
                .commit(); //nao adiciona ao BackStack
    }

    //metodo para gerenciar a visibilidade dos botoes e tambem pra trocar em cima a cor
    public void setBotaoVisibilidade(boolean visivel) {
        int visibilidade = visivel ? View.VISIBLE : View.GONE;
        botaoTopico1.setVisibility(visibilidade);
        botaoTopico2.setVisibility(visibilidade);
        botaoTopico3.setVisibility(visibilidade);
        logoUni.setVisibility(visibilidade);
        logoOut.setVisibility(visibilidade);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.light_blue));
    }
}
