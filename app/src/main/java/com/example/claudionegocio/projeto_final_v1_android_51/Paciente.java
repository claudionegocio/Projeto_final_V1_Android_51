package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Paciente extends AppCompatActivity {
    public static final String TAG = "Paciente";

    EditText editNome;
    RadioGroup rdioIdade, rdioEscolaridade;
    RadioButton radioIdade, radioEscola;
    Button btnPrep, btnView;
    private PacienteDAO pacienteDAO;


    private long Id;
    private String nome;
    private String idade;
    private String escolaridade;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paciente);

        editNome = (EditText) findViewById(R.id.edt_Nome);
        btnPrep = (Button) findViewById(R.id.btnPrep);
        btnView = (Button) findViewById(R.id.btnVisualiza);
        rdioIdade = (RadioGroup) findViewById(R.id.rdioIdade);
        rdioEscolaridade = (RadioGroup) findViewById(R.id.rdioEscolaridade);
        this.pacienteDAO = PacienteDAO.getInstance(this);



        abrePreparacao();
        viewAll();

    }


    public long getId() {
        return Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }
    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }





    public void viewAll(){
        btnView.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res  = pacienteDAO.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Erro","Sem dados");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("id: "+ res.getString(0)+"\n");
                            buffer.append("Nome Paciente: "+ res.getString(1)+"\n");
                            buffer.append("Idade Paciente: "+ res.getString(2)+"\n");
                            buffer.append("Escola Paciente: "+ res.getString(3)+"\n");

                        }

                        //show all data
                        showMessage("Dados",buffer.toString());
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

    /*public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/



    public void abrePreparacao(){

        btnPrep.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int selecao_idade = rdioIdade.getCheckedRadioButtonId();
                        int selecao_escolaridade = rdioEscolaridade.getCheckedRadioButtonId();


                        radioIdade = (RadioButton) findViewById(selecao_idade);
                        radioEscola = (RadioButton) findViewById(selecao_escolaridade);

                        nome = editNome.getText().toString();
                        escolaridade = radioEscola.getText().toString();
                        idade = radioIdade.getText().toString();

                       /* boolean isInserted = pacienteDAO.inserirDados(
                                "cacal",
                                "33",
                                "aaaa"
                        );

                        if (isInserted)
                            Toast.makeText(Paciente.this,"Dados Inseridos",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Paciente.this,"Dados Não Foram Inseridos",Toast.LENGTH_LONG).show();*/

                       Paciente pacienteCriado = pacienteDAO.inserirDados(
                                nome,
                                idade,
                                escolaridade
                        );



                        Log.d(TAG, "paciente adicionado: "+ pacienteCriado.getNome());
                        Intent intent = new Intent();
                        //intent.putExtra(ListCompaniesActivity.EXTRA_ADDED_COMPANY, createdCompany);
                        setResult(RESULT_OK, intent);
                        finish();

                        Cursor res = pacienteDAO.getAllData();
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("id: " + res.getString(0) + "\n");
                            buffer.append("Nome Paciente: " + res.getString(1) + "\n");
                            buffer.append("Idade Paciente: " + res.getString(2) + "\n");
                            buffer.append("Escola Paciente: " + res.getString(3) + "\n");

                            id= res.getInt(0);
                        }

                        Bundle params = new Bundle();
                        params.putInt("Id", id);

                        intent = new Intent(Paciente.this, TestMemoria.class);
                        intent.putExtras(params);
                        startActivity(intent);

                        //startActivity(new Intent(Paciente.this, TestMemoria.class));
                    }
                }

        );




    }

}
