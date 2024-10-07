package com.example.projetoextensao;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentoInicial extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicial, container, false);

        //isso vai fazer um timer para mudar automaticamente para a tela principal depois de 3 segundos
        new Handler().postDelayed(() -> {
            if (getActivity() != null) {

                //trocar para a tela principal sem adicionar o FragmentoInicial na pilha do backstack
                ((MainActivity) getActivity()).trocarFragmentoSemVoltar(new FragmentoPrincipal());

            }
        }, 3000);

        return view;
    }
}

