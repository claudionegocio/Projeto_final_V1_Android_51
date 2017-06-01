package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PacienteAntigo extends AppCompatActivity {
    Spinner spn;
    Button btnNovo, btnContinuar;
    private  PacienteDAO pacienteDAO;
    private Testes testes;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_antigo);
        this.pacienteDAO = PacienteDAO.getInstance(this);
        btnNovo = (Button) findViewById(R.id.btn_novo);
        btnContinuar = (Button) findViewById(R.id.btn_continuar);
        testes = new Testes();

        //Lista de Pacientes
        final List<String> opcoes = new ArrayList<String>();


        Cursor res = pacienteDAO.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("id: " + res.getString(0) + "\n");
            buffer.append("Nome Paciente: " + res.getString(1) + "\n");
            buffer.append("Idade Paciente: " + res.getString(2) + "\n");
            buffer.append("Escola Paciente: " + res.getString(3) + "\n");

            opcoes.add(res.getString(1));
        }


        //Adaptador com o Layout e as informacoes do spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes);

        //Spinner preenchido
        spn = (Spinner) findViewById(R.id.spn);
        spn.setAdapter(adapter);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //opcoes.get(i);
                Toast.makeText(PacienteAntigo.this,Integer.toString(i+1), Toast.LENGTH_SHORT).show();
                id = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        abreNovo();
        abreTestes();

    }

    public void abreNovo(){

        btnNovo.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {



                        startActivity(new Intent(PacienteAntigo.this, Paciente.class));
                    }
                }

        );




    }

    public void abreTestes(){

        btnContinuar.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        Bundle params = new Bundle();
                        params.putInt("Id", id);

                        Intent intent = new Intent(PacienteAntigo.this, TestMemoria.class);
                        intent.putExtras(params);
                        startActivity(intent);
                    }
                }

        );




    }


}
