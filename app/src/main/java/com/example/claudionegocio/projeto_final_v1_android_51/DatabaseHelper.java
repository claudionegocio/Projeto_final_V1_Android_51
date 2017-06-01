package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    public static final String TAG = "DBHelper";
    public static final String DATABASE_NAME = "Projeto.db";
    private static final int DATABASE_VERSION = 3;


    //tabela do paciente
    public static final String TABLE_PACIENTE = "Paciente";
    public static final String COLUNA_PACIENTE_ID = "ID";
    public static final String COLUNA_PACIENTE_NOME = "NOME_PACIENTE";
    public static final String COLUNA_PACIENTE_IDADE = "IDADE_PACIENTE";
    public static final String COLUNA_PACIENTE_ESCOLA = "ESCOLARIDADE_PACIENTE";

    //tabela dos testes
    public static final String TABLE_TESTES = "Resultados_teste";
    public static final String COLUNA_TESTES_ID = COLUNA_PACIENTE_ID;
    public static final String COLUNA_TESTES_ORIENTACAO_TEMPORAL = "TEMPORAL";
    public static final String COLUNA_TESTES_ORIENTACAO_ESPACIAL = "ESPACIAL";
    public static final String COLUNA_TESTES_REGISTRO = "REGISTRO";
    public static final String COLUNA_TESTES_ATENCAO_E_CALCULO = "ATENCA_E_CALCULO";
    public static final String COLUNA_TESTES_MEMORIA_E_EVOCACAO = "MEMORIA_E_EVOCACAO";
    public static final String COLUNA_TESTES_NOMEAR_OBJETOS = "NOMEAR_OBJETOS";
    public static final String COLUNA_TESTES_REPETIR = "REPETIR";
    public static final String COLUNA_TESTES_COMANDOS = "COMANDOS";
    public static final String COLUNA_TESTES_ESCREVER = "ESCREVER";
    public static final String COLUNA_TESTES_LEITURA_E_EXECUCAO = "LEITURA_E_EXECUCAO";
    public static final String COLUNA_TESTES_DIAGRAMA = "DIAGRAMA";



    //SQL para criação da tabela do paciente
    private static final String SQL_CREATE_TABLE_PACIENTE = "create table " + TABLE_PACIENTE + "(" +
            COLUNA_PACIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUNA_PACIENTE_NOME + " TEXT, " +
            COLUNA_PACIENTE_IDADE + " TEXT, " +
            COLUNA_PACIENTE_ESCOLA + " TEXT);";


    //SQL para criação da tabela dos testes
    private static final String SQL_CREATE_TABLE_TESTES = "create table " + TABLE_TESTES + "(" +
            COLUNA_TESTES_ID + " INTEGER, " +
            COLUNA_TESTES_ORIENTACAO_TEMPORAL + " INTEGER, " +
            COLUNA_TESTES_ORIENTACAO_ESPACIAL + " INTEGER, " +
            COLUNA_TESTES_REGISTRO + " INTEGER, " +
            COLUNA_TESTES_ATENCAO_E_CALCULO + " INTEGER, " +
            COLUNA_TESTES_MEMORIA_E_EVOCACAO + " INTEGER, " +
            COLUNA_TESTES_NOMEAR_OBJETOS + " INTEGER, "        +
            COLUNA_TESTES_REPETIR + " INTEGER, " +
            COLUNA_TESTES_COMANDOS + " INTEGER, " +
            COLUNA_TESTES_ESCREVER + " INTEGER,  " +
            COLUNA_TESTES_LEITURA_E_EXECUCAO + " INTEGER, " +
            COLUNA_TESTES_DIAGRAMA + " INTEGER);";


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static DatabaseHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }




    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PACIENTE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TESTES);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {
        Log.w(TAG,
                "Atualizando o banco de dados da versão" + versaoAntiga + " para "+ versaoNova);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_PACIENTE);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_TESTES);
        onCreate(sqLiteDatabase);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


}
    /*
    public boolean insertData(String nome_cuidador, String nome_paciente){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nome_cuidador);
        contentValues.put(COL_3,nome_paciente);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
            else
            return true;
    }

   public boolean insertDataTabela2(String idade_paciente, String escolaridade_paciente){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUNA_PACIENTE_ID,"Cula");
        contentValues.put(COLUNA_PACIENTE_NOME,idade_paciente);
        contentValues.put(COLUNA_PACIENTE_IDADE,escolaridade_paciente);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME, null);
        return res;

    }

    public Integer updateData(String id, String nome_cuidador, String nome_paciente){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nome_cuidador);
        contentValues.put(COL_3,nome_paciente);

       return sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });

    }

    public Integer deleteData (String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // A função ".delete" retorna interger (1 se apagou e 0 se não apagou)
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] { id });
*/









