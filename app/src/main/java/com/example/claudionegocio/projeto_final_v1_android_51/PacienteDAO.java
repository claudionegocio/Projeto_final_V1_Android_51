package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.makeText;

/**
 * Created by claudionegocio on 30/03/2017.
 */

public class PacienteDAO {

    private static PacienteDAO mInstance = null;
    public static final String TAG = "PacienteDAO";

    private SQLiteDatabase pacienteDatabase;
    private DatabaseHelper pacienteDBHelper;
    private Context pContext;
    private String[] pTodasColunas = {  pacienteDBHelper.COLUNA_PACIENTE_ID,
                                        pacienteDBHelper.COLUNA_PACIENTE_NOME,
                                        pacienteDBHelper.COLUNA_PACIENTE_IDADE,
                                        pacienteDBHelper.COLUNA_PACIENTE_ESCOLA
    };



    private PacienteDAO (Context context) {
        this.pContext = context;

        //teste
        /*pacienteDBHelper = new DatabaseHelper(context);*/

        //novo
        pacienteDBHelper = DatabaseHelper.getInstance(pContext);

        try { open(); }
        catch (SQLiteException e){
            Log.e(TAG, "SQLException ao abrir o banco de dados " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static PacienteDAO getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new PacienteDAO(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public void open() throws SQLiteException {
        pacienteDatabase = pacienteDBHelper.getWritableDatabase();
    }


    public void close() {pacienteDBHelper.close();}



    //original
    public Paciente inserirDados(String nome_paciente, String idade, String escolaridade){

        SQLiteDatabase sqLiteDatabase = pacienteDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_NOME,nome_paciente);
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_IDADE,idade);
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_ESCOLA,escolaridade);
        long insertId = pacienteDatabase.insert(pacienteDBHelper.TABLE_PACIENTE, null,contentValues);

        Cursor cursor = pacienteDatabase.query(pacienteDBHelper.TABLE_PACIENTE, pTodasColunas,
                pacienteDBHelper.COLUNA_PACIENTE_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Paciente novoPaciente = cursorToPaciente(cursor);
        cursor.close();

        return novoPaciente;
    }

    //modificado
    /*
    public boolean inserirDados(String nome_paciente, String idade, String escolaridade){



        SQLiteDatabase sqLiteDatabase = pacienteDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_NOME,nome_paciente);
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_IDADE,idade);
        contentValues.put(pacienteDBHelper.COLUNA_PACIENTE_ESCOLA,escolaridade);
        long resultado = pacienteDatabase.insert(pacienteDBHelper.TABLE_PACIENTE, null,contentValues);

        Cursor cursor = sqLiteDatabase.query(pacienteDBHelper.TABLE_PACIENTE, pTodasColunas,
                pacienteDBHelper.COLUNA_PACIENTE_ID + " = " + resultado, null, null,
                null, null);
        cursor.moveToFirst();
        Paciente novoPaciente = cursorToPaciente(cursor);
        cursor.close();
        if (resultado == -1)
            return false;
        else
            return true;
    }*/

    public void deletePaciente (Long id){
        TestesDAO testesDAO = TestesDAO.getInstance(pContext);
        List<Testes> listTestes = testesDAO.getTestesDoPaciente(id);
                if (listTestes != null && !listTestes.isEmpty()) {
            for (Testes t : listTestes) {
                testesDAO.deleteTestes(t);
            }
        }

        System.out.println("O paciente deletado tem o id: " + id);
        pacienteDatabase.delete(DatabaseHelper.TABLE_PACIENTE, DatabaseHelper.COLUNA_PACIENTE_ID
                + " = " + id, null);
    }

    public Paciente getPacienteById(long id) {
        Cursor cursor = pacienteDatabase.query(DatabaseHelper.TABLE_PACIENTE, pTodasColunas,
                DatabaseHelper.COLUNA_PACIENTE_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Paciente paciente = cursorToPaciente(cursor);
        return paciente;
    }


    protected Paciente cursorToPaciente(Cursor cursor) {
        Paciente paciente = new Paciente();
        paciente.setId(cursor.getLong(0));
        paciente.setNome(cursor.getString(1));
        paciente.setIdade(cursor.getString(2));
        paciente.setEscolaridade(cursor.getString(3));
        return paciente;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = pacienteDBHelper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ pacienteDBHelper.TABLE_PACIENTE, null);
        return res;

    }
}
