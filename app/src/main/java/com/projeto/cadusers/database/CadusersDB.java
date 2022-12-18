package com.projeto.cadusers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CadusersDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cadusers.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USUARIOS = "usuarios";

    public CadusersDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(id     INTEGER PRIMARY KEY," +
                                                                  " nome   VARCHAR, " +
                                                                  " email  VARCHAR, " +
                                                                  " senha  VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUsuario(UsuarioDAO usuario){

        Log.i("insertUsuario", "Email: " + usuario.getEmail());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("senha", usuario.getSenha());

        sqLiteDatabase.insert(TABLE_USUARIOS, null, contentValues);
        sqLiteDatabase.close();
    }

    public UsuarioDAO selectUsuario(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USUARIOS, new String[]{"nome", "email", "senha"}, "email = ?", new String[]{email}, null, null, null);

        UsuarioDAO usuario = null;

        if (cursor.moveToFirst()){
            usuario = new UsuarioDAO(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            Log.i("selectUsuario", "Usuario: " + usuario.getNome());
        }

        cursor.close();

        return usuario;
    }

    public List<UsuarioDAO> listarUsuarios(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT nome, email FROM " + TABLE_USUARIOS;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        List<UsuarioDAO> listaUsuarios = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{
                UsuarioDAO usuario = new UsuarioDAO(cursor.getString(0), cursor.getString(1), null);
                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaUsuarios;
    }
}
