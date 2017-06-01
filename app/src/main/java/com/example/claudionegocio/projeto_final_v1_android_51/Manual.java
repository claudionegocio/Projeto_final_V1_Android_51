package com.example.claudionegocio.projeto_final_v1_android_51;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manual extends AppCompatActivity {
    public Button btn_entrada;
    private PacienteDAO pacienteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        btn_entrada = (Button) findViewById(R.id.btn_entrada);
        this.pacienteDAO = PacienteDAO.getInstance(this);
        abreEntrada();
        System.out.println("Passei por aqui");
    }


    private void abreEntrada() {

        btn_entrada.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Cursor res  = pacienteDAO.getAllData();
                        if(res.getCount() == 0){

                            startActivity(new Intent(Manual.this, Paciente.class));
                        }
                        else { startActivity(new Intent(Manual.this, PacienteAntigo.class));}
                    }
                }

        );

    }

}
