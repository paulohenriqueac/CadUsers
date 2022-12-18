package com.projeto.cadusers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button buttonCadastrar;
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        buttonCadastrar.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, CadastrarActivity.class);
            startActivity(intent);
        });

        Button buttonListar;
        buttonListar = findViewById(R.id.buttonListar);
        buttonListar.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, ListarActivity.class);
            startActivity(intent);
        });

        Button buttonSair;
        buttonSair = findViewById(R.id.buttonSair);
        buttonSair.setOnClickListener(view -> {
            Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}