package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;


public class Entrada_usuario extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editCuidador, editPaciente, editId;
    Button btnAddData, btnview, btnUpdate, btnDelete, btnPaciente;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_usuario);
        myDB = new DatabaseHelper(this);

        editCuidador = (EditText) findViewById(R.id.editCuidador);
        editPaciente = (EditText) findViewById(R.id.editPaciente);
        editId = (EditText) findViewById(R.id.editId);

        btnAddData = (Button) findViewById(R.id.btnAdd);
        btnview = (Button) findViewById(R.id.btnView);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnDelete= (Button) findViewById(R.id.btnDelete);
        btnPaciente = (Button) findViewById(R.id.btnPaciente);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        abrePaciente();

    }


    public void AddData(){
        btnAddData.setOnClickListener(

                new View.OnClickListener(){

                    public void onClick(View v){
                        boolean isInserted = myDB.insertData(
                                editCuidador.getText().toString(),
                                editPaciente.getText().toString()
                                );

                        if (isInserted)
                            Toast.makeText(Entrada_usuario.this,"Dados Inseridos",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Entrada_usuario.this,"Dados Não Foram Inseridos",Toast.LENGTH_LONG).show();
                    }

                }

        );
    }


    public void viewAll(){
        btnview.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res  = myDB.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Erro","Sem dados");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id: "+ res.getString(0)+"\n");
                            buffer.append("Nome cuidador: "+ res.getString(1)+"\n");
                            buffer.append("Nome Paciente: "+ res.getString(2)+"\n");

                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }

        );


    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();



    }



    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer isUpdate = myDB.updateData(editId.getText().toString(),editCuidador.getText().toString(),
                                editPaciente.getText().toString());
                        if (isUpdate>0)
                            Toast.makeText(Entrada_usuario.this,"Dados Atualizados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Entrada_usuario.this,"Dados Não Foram Atualizados",Toast.LENGTH_LONG).show();
                    }
                }


        );
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDB.deleteData(editId.getText().toString());
                        if (deletedRows > 0) Toast.makeText(Entrada_usuario.this,"Dados Apagados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Entrada_usuario.this,"Dados Não Foram Apagados",Toast.LENGTH_LONG).show();

                    }
                }

        );
    }


    private void abrePaciente() {

        btnPaciente.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Entrada_usuario.this, Paciente.class));
                    }
                }

        );

    }

*/

}
