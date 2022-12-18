package com.projeto.cadusers;

import static java.util.Objects.isNull;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.cadusers.database.CadusersDB;
import com.projeto.cadusers.database.UsuarioDAO;

import java.util.Objects;

public class CadastrarActivity extends AppCompatActivity {
    EditText editTextNome, editTextEmail, editTextSenha, editTextConfirmar;
    Button buttonEfetuarCadastro;

    UsuarioDAO resultUsuario;
    CadusersDB cadUsersDB = new CadusersDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextConfirmar = findViewById(R.id.editTextConfirmar);
        buttonEfetuarCadastro = findViewById(R.id.buttonCadastrar);

        buttonEfetuarCadastro.setOnClickListener(view -> {

            String nome = editTextNome.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();
            String confirmar = editTextConfirmar.getText().toString().trim();

            if ((!nome.isEmpty()) && (!email.isEmpty()) && (!senha.isEmpty()) && (!confirmar.isEmpty())){

                // Verificar se o Email ja foi cadastrado---------------------------------------------------------------------
                resultUsuario = cadUsersDB.selectUsuario(email);
                if (isNull(resultUsuario)) {
                    if (senha.equals(confirmar)) {
                        cadUsersDB.insertUsuario(new UsuarioDAO(nome, email, senha));

                        editTextNome.setText("");
                        editTextEmail.setText("");
                        editTextSenha.setText("");
                        editTextConfirmar.setText("");

                        Toast.makeText(CadastrarActivity.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CadastrarActivity.this, "Senha inválida, favor confirmar!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(CadastrarActivity.this, "Email já cadastrado!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(CadastrarActivity.this, "Dados inválidos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}