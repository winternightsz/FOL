package com.example.projetoextensao.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetoextensao.R;

public class FragmentoTopico3 extends Fragment {
    private TextView opcoes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_topico1, container, false);

        Spinner filtro = view.findViewById(R.id.spinnerFilter);

        // Adiciona as opções de tempo ao Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.filtro_opcoes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filtro.setAdapter(adapter);

        // Listener para quando uma opção for selecionada
        filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String opcaoSelecionada = parentView.getItemAtPosition(position).toString(); // Pega a opção selecionada
                atualizarGrafico(opcaoSelecionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Não faz nada se nada for selecionado
            }
        });

        return view;
    }

    //atualizar o gráfico com base na cidade
    private void atualizarGrafico(String opcoes) {
        if (opcoes.equals("24 horas")) {
            //atualizar
        } else if (opcoes.equals("7 dias")) {
            //atualizar
        } else if (opcoes.equals("15 dias")) {
            //atualizar
        } else if (opcoes.equals("1 mês")) {
            //atualizar
        }
    }
}