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

        // Mostrar a tela inicial por 3 segundos antes de mudar para o propximo fragmento
        new Handler().postDelayed(() -> {
            if (getActivity() != null) {
                MainActivity mainActivity = (MainActivity) getActivity();

                // Trocar para o FragmentoPrincipal
                mainActivity.trocarFragmentoSemVoltar(new FragmentoPrincipal());

                // Tornar os botoes visiveis
                mainActivity.setBotaoVisibilidade(true);
            }
        }, 3000);

        return view;
    }
}
