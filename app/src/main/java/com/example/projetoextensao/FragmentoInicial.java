package com.example.projetoextensao;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoextensao.fragmentos.FragmentoTopico1;

public class FragmentoInicial extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicial, container, false);

        // Exibir a tela inicial por 3 segundos antes de mudar para o próximo fragmento
        new Handler().postDelayed(() -> {
            if (getActivity() != null) {
                MainActivity mainActivity = (MainActivity) getActivity();

                // Trocar para o FragmentoTopico1
                mainActivity.trocarFragmentoSemVoltar(new FragmentoTopico1());

                // Tornar os botões visíveis
                mainActivity.setBotaoVisibilidade(true);
            }
        }, 3000);

        return view;
    }
}
