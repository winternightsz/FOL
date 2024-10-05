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

public class FragmentoTopico1 extends Fragment {
    private TextView cityName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_topico1, container, false);

        // Pegue o Spinner
        Spinner spinner = view.findViewById(R.id.spinnerFilter);

        // Pegue o TextView para mostrar o nome da cidade
        cityName = view.findViewById(R.id.cityName);

        // Adicione o array de strings (as cidades) ao Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Defina o listener para quando uma cidade for selecionada
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Pega a cidade selecionada
                String selectedCity = parentView.getItemAtPosition(position).toString();

                // Aqui você pode atualizar o gráfico com base na cidade selecionada
                atualizarGrafico(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nada acontece se nenhuma cidade for selecionada
            }
        });

        return view;
    }

    // Método para atualizar o gráfico com base na cidade
    private void atualizarGrafico(String cidade) {
        // Lógica para atualizar o gráfico baseado na cidade selecionada
        // Exemplo:
        if (cidade.equals("Itajaí")) {
            // Atualize o gráfico para mostrar os dados de Itajaí
        } else if (cidade.equals("Balneário Camboriú")) {
            // Atualize o gráfico para Balneário Camboriú
        } else if (cidade.equals("Penha")) {
            // Atualize o gráfico para Penha
        }
    }
}
