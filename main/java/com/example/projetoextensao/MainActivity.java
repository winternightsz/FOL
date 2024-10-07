package com.example.projetoextensao;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vai mostrar o fragmento inicial (logozinha) quando iniciar o app
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentoInicial())
                    .commit();
        }

        //vai configurar o OnBackPressedDispatcher para controlar o botao de voltar
        //porque se nao ta voltando pra logo
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //vai verificar se tem fragmentos no back stack
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();  //voltar ao fragmento anterior
                } else {
                    finish();  //fecha o app se nao tiver fragmentos na pilha
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }


    //metodo para trocar fragmentos sem adicionar a pilha de voltar
    public void trocarFragmentoSemVoltar(Fragment fragmento) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmento)
                .commit();  //nao usa addToBackStack
    }


    //fragments lib
    //Add this transaction to the back stack.
    // This means that the transaction will be remembered after it is committed,
    // and will reverse its operation when later popped off the stack.
    //setReorderingAllowed(boolean) must be set to true in the same
    // transaction as addToBackStack() to allow the pop of that transaction to be reordered.

    //metodo padrao para trocar fragmentos com back stack
    public void trocarFragmento(Fragment fragmento) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmento)
                .addToBackStack(null)
                .commit();
    }



//foi deprecated entao usa outro
//    @Override
//    public void onBackPressed() {
//
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed(); //fechar o app se nao houver fragmentos na pilha
//        }
//    }

}

