package com.example.projetoextensao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetoextensao.fragmentos.FragmentoTopico1;
import com.example.projetoextensao.fragmentos.FragmentoTopico2;
import com.example.projetoextensao.fragmentos.FragmentoTopico3;

public class FragmentoPrincipal extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_principal, container, false);

        // Configurando os botões para trocar fragmentos
        view.findViewById(R.id.botaoTopico1).setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).trocarFragmento(new FragmentoTopico1());
            }
        });

        view.findViewById(R.id.botaoTopico2).setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).trocarFragmento(new FragmentoTopico2());
            }
        });

        view.findViewById(R.id.botaoTopico3).setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).trocarFragmento(new FragmentoTopico3());
            }
        });

        return view;
    }
}
