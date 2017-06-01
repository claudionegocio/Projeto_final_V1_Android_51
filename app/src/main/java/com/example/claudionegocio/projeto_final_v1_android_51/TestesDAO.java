package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudionegocio on 02/05/2017.
 */

public class TestesDAO {
    private static TestesDAO mInstance = null;
    public static final String TAG = "TestesDAO";
    private Context tContext;


    // Database fields
    private SQLiteDatabase testesDatabase;
    private DatabaseHelper testesDbHelper;
    private String[] tTodasColunas = {
            DatabaseHelper.COLUNA_TESTES_ID,
            DatabaseHelper.COLUNA_TESTES_ORIENTACAO_TEMPORAL,
            DatabaseHelper.COLUNA_TESTES_ORIENTACAO_ESPACIAL,
            DatabaseHelper.COLUNA_TESTES_REGISTRO,
            DatabaseHelper.COLUNA_TESTES_ATENCAO_E_CALCULO,
            DatabaseHelper.COLUNA_TESTES_MEMORIA_E_EVOCACAO,
            DatabaseHelper.COLUNA_TESTES_NOMEAR_OBJETOS,
            DatabaseHelper.COLUNA_TESTES_REPETIR,
            DatabaseHelper.COLUNA_TESTES_COMANDOS,
            DatabaseHelper.COLUNA_TESTES_ESCREVER,
            DatabaseHelper.COLUNA_TESTES_LEITURA_E_EXECUCAO,
            DatabaseHelper.COLUNA_TESTES_DIAGRAMA
    };

    private TestesDAO(Context context) {
        testesDbHelper = DatabaseHelper.getInstance(context);
        this.tContext = context;
        // Abrir o banco de dados
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException ao abrir o banco de dados " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static TestesDAO getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new TestesDAO(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public void open() throws SQLException {
        testesDatabase = testesDbHelper.getWritableDatabase();
    }

    public void close() {
        testesDbHelper.close();
    }

    public void inserirRespostas(long id,
                                   int temporal,
                                   int espacial,
                                   int registro,
                                   int atencao,
                                   int memoria,
                                   int nomear,
                                   int repetir,
                                   int comandos,
                                   int escrever,
                                   int leitura,
                                   int diagrama) {
        SQLiteDatabase sqLiteDatabase = testesDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUNA_TESTES_ID, id);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_ORIENTACAO_TEMPORAL, temporal);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_ORIENTACAO_ESPACIAL, espacial);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_REGISTRO, registro);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_ATENCAO_E_CALCULO, atencao);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_MEMORIA_E_EVOCACAO, memoria);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_NOMEAR_OBJETOS, nomear);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_REPETIR, repetir);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_COMANDOS, comandos);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_ESCREVER, escrever);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_LEITURA_E_EXECUCAO, leitura);
        contentValues.put(DatabaseHelper.COLUNA_TESTES_DIAGRAMA, diagrama);

        long insertId = testesDatabase
                .insert(DatabaseHelper.TABLE_TESTES, null,contentValues);
        Cursor cursor = testesDatabase.query(DatabaseHelper.TABLE_TESTES, tTodasColunas,
                DatabaseHelper.COLUNA_PACIENTE_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
      //  Testes newTeste = cursorToTestes(cursor);
        cursor.close();
       // return newTeste;
    }

    public void deleteTestes(Testes teste) {
        long id = teste.getId();
        System.out.println("O teste deletaddo tem o id: " + id);
        testesDatabase.delete(DatabaseHelper.TABLE_TESTES, DatabaseHelper.COLUNA_TESTES_ID
                + " = " + id, null);
    }

    public List<Testes> getAllTestes() {
        List<Testes> listTestes = new ArrayList<Testes>();

        Cursor cursor = testesDatabase.query(DatabaseHelper.TABLE_TESTES, tTodasColunas,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Testes employee = cursorToTestes(cursor);
            listTestes.add(employee);
            cursor.moveToNext();
        }
        // fechando o cursor
        cursor.close();
        return listTestes;
    }

    public List<Testes> getTestesDoPaciente(long pacienteId) {
        List<Testes> listTestes = new ArrayList<Testes>();

        Cursor cursor = testesDatabase.query(DatabaseHelper.TABLE_TESTES, tTodasColunas,
                DatabaseHelper.COLUNA_TESTES_ID + " = ?",
                new String[] { String.valueOf(pacienteId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Testes testes = cursorToTestes(cursor);
            listTestes.add(testes);
            cursor.moveToNext();
        }
        // fechando o cursor
        cursor.close();
        return listTestes;
    }

    private Testes cursorToTestes(Cursor cursor) {
        Testes testes = new Testes();
        testes.setId(cursor.getInt(0));
        /*testes.setQ1(cursor.getString(1));
        testes.setQ2(cursor.getString(2));
        testes.setQ3(cursor.getString(3));*/



        //Parece repetido a coluna original
        long pacienteId = cursor.getLong(0);
        PacienteDAO dao = PacienteDAO.getInstance(tContext);
        Paciente paciente = dao.getPacienteById(pacienteId);

        if (paciente != null)
            testes.setPaciente(paciente);

        return testes;
    }



    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = testesDbHelper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ testesDbHelper.TABLE_TESTES, null);
        return res;

    }

    public Cursor getDadosPorId(int ID){
        SQLiteDatabase sqLiteDatabase = testesDbHelper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ testesDbHelper.TABLE_TESTES + " where id = " + ID, null);
        return res;

    }

}
