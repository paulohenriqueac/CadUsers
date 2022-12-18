package com.projeto.cadusers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.cadusers.database.CadusersDB;
import com.projeto.cadusers.database.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListarActivity extends AppCompatActivity {
    CadusersDB cadUsersDB = new CadusersDB(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    ListView listViewUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Objects.requireNonNull(getSupportActionBar()).hide();

        listViewUsuarios = findViewById(R.id.listViewUsuarios);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(ListarActivity.this, R.layout.my_simple_list_item, arrayList);

        listViewUsuarios.setAdapter(adapter);

        List<UsuarioDAO> listaUsuarios = cadUsersDB.listarUsuarios();

        for (UsuarioDAO usuario : listaUsuarios){
            arrayList.add("Nome: " + usuario.getNome() + "\nEmail : " + usuario.getEmail());
            adapter.notifyDataSetChanged();
        }
    }
}