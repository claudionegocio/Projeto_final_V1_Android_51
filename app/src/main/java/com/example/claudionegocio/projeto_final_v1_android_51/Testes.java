package com.example.claudionegocio.projeto_final_v1_android_51;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Testes extends AppCompatActivity {

    private long Id;
    /*private String q1;
    private String q2;
    private String q3;*/



    private Paciente paciente;

    public long getId() {        return Id;    }

    public void setId(long id) { id = id;    }

  /*  public String getQ1() {      return q1;    }

    public void setQ1(String q1) {this.q1 = q1;    }

    public String getQ2() {       return q2;    }

    public void setQ2(String q2) {this.q2 = q2;    }

    public String getQ3() {       return q3;    }

    public void setQ3(String q3) {this.q3 = q3;    }*/

    public Paciente getPaciente() {                 return paciente;    }

    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testes);
    }
}
