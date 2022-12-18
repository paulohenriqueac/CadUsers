package com.projeto.cadusers;


import static java.util.Objects.isNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.projeto.cadusers.database.CadusersDB;
import com.projeto.cadusers.database.UsuarioDAO;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextSenha;
    Button   buttonEntrar;

    UsuarioDAO resultUsuario;
    CadusersDB cadUsersDB = new CadusersDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        // Criar o usuário Administrador -----------------------------------------------------------------------------
        resultUsuario = cadUsersDB.selectUsuario("admin@cadusers.com");
        if (isNull(resultUsuario)) {
            cadUsersDB.insertUsuario(new UsuarioDAO("Administrador", "admin@cadusers.com", "admin"));
        }
        //------------------------------------------------------------------------------------------------------------

        buttonEntrar.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();

            if ((!email.isEmpty()) && (!senha.isEmpty())) {
                resultUsuario = cadUsersDB.selectUsuario(email);

                if (!isNull(resultUsuario)) {
                    if (resultUsuario.getSenha().equals(senha)) {
                        Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha inválida!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Dados inválidos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}