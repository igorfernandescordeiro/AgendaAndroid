package com.example.ifcig.agendaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import persistencia.ContatoDTO;
import persistencia.DataBase;

public class TelaEditarActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText celular;
    private ImageButton bSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar);

        nome = (EditText) findViewById(R.id.editTextNome2);
        email = (EditText) findViewById(R.id.editTextEmail2);
        celular = (EditText) findViewById(R.id.editTextCelular2);
        bSalvar = (ImageButton) findViewById(R.id.imageButtonSalvar2);

        nome.setText(getIntent().getExtras().get("nome").toString());
        email.setText(getIntent().getExtras().get("email").toString());
        celular.setText(getIntent().getExtras().get("celular").toString());

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean erro = false;

                if (nome.getText().toString().isEmpty()){
                    nome.setError("Preencha!");
                    erro = true;
                }

                if (email.getText().toString().isEmpty()){
                    email.setError("Preencha!");
                    erro = true;
                }

                if (celular.getText().toString().isEmpty()){
                    celular.setError("Preencha!");
                    erro = true;
                }

                if(!erro){
                    DataBase dataBase = new DataBase(TelaEditarActivity.this);

                    dataBase.atualizar(new ContatoDTO(
                            Integer.parseInt(getIntent().getExtras().get("id").toString()),
                            nome.getText().toString(),
                            email.getText().toString(),
                            celular.getText().toString()
                    ));

                    Toast.makeText(TelaEditarActivity.this, "Atualizado!", Toast.LENGTH_LONG).show();

                    finish();
                }

            }
        });
    }
}
