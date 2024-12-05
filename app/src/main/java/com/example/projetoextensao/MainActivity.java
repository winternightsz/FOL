package com.example.projetoextensao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.projetoextensao.fragmentos.FragmentoTopico1;
import com.example.projetoextensao.fragmentos.FragmentoTopico2;
import com.example.projetoextensao.fragmentos.FragmentoTopico3;

public class MainActivity extends AppCompatActivity {

    private Button botaoSelecionado; // Variável para armazenar o botão atualmente selecionado
    private Button botaoTopico1, botaoTopico2, botaoTopico3; // Botões principais
    private ImageView logoUni, logoOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botões
        botaoTopico1 = findViewById(R.id.botaoTopico1);
        botaoTopico2 = findViewById(R.id.botaoTopico2);
        botaoTopico3 = findViewById(R.id.botaoTopico3);

        logoUni = findViewById(R.id.logoUnivali);
        logoOut = findViewById(R.id.logoOutro);

        // Esconder botões inicialmente
        setBotaoVisibilidade(false);

        // Configurar listener para troca de fragmentos e atualização do estado do botão
        View.OnClickListener listener = view -> {
            // Redefinir o estado do botão anteriormente selecionado
            if (botaoSelecionado != null) {
                botaoSelecionado.setSelected(false);
            }

            // Marcar o botão clicado como selecionado
            view.setSelected(true);
            botaoSelecionado = (Button) view; // Atualizar o botão selecionado

            // Trocar para o fragmento correspondente
            if (view.getId() == R.id.botaoTopico1) {
                trocarFragmento(new FragmentoPrincipal());
            } else if (view.getId() == R.id.botaoTopico2) {
                trocarFragmento(new FragmentoTopico2());
            } else if (view.getId() == R.id.botaoTopico3) {
                trocarFragmento(new FragmentoTopico3());
            }
        };

        // Configurar listeners nos botões
        botaoTopico1.setOnClickListener(listener);
        botaoTopico2.setOnClickListener(listener);
        botaoTopico3.setOnClickListener(listener);

        // Mostrar o FragmentoInicial
        if (savedInstanceState == null) {
            trocarFragmento(new FragmentoInicial());
            botaoTopico1.setSelected(true); // Marcar o primeiro botão como selecionado
            botaoSelecionado = botaoTopico1; // Atualizar o botão selecionado
        }
    }

    // Método para trocar fragmentos
    public void trocarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void trocarFragmentoSemVoltar(Fragment fragmento) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmento)
                .commit(); // Não adiciona ao BackStack
    }

    // Método para gerenciar a visibilidade dos botões
    public void setBotaoVisibilidade(boolean visivel) {
        int visibilidade = visivel ? View.VISIBLE : View.GONE;
        botaoTopico1.setVisibility(visibilidade);
        botaoTopico2.setVisibility(visibilidade);
        botaoTopico3.setVisibility(visibilidade);
        logoUni.setVisibility(visibilidade);
        logoOut.setVisibility(visibilidade);
    }
}
