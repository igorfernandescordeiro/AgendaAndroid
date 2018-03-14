package com.example.ifcig.agendaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import persistencia.ContatoDTO;
import persistencia.DataBase;

public class MainActivity extends AppCompatActivity {

    private EditText tNome, tEmail, tCelular;
    private ImageButton bPesquisar, bAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tNome = (EditText) findViewById(R.id.editTextNome);
        tEmail = (EditText) findViewById(R.id.editTextEmail);
        tCelular = (EditText) findViewById(R.id.editTextCelular);
        bPesquisar = (ImageButton) findViewById(R.id.buttonPesquisar);
        bAdicionar = (ImageButton) findViewById(R.id.buttonAdicionar);

        bPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contatos = new Intent(MainActivity.this, ContatosActivity.class);
                startActivity(contatos);
            }
        });

        bAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean erro = false;
                if (tNome.getText().toString().isEmpty()){
                    tNome.setError("Digitar nome.");
                    erro = true;
                }
                if(tEmail.getText().toString().isEmpty()){
                    tEmail.setError("Digitar email. ");
                    erro = true;
                }
                if(tCelular.getText().toString().isEmpty()){
                    tCelular.setError("Digitar celular.");
                    erro = true;
                }

                if(!erro){
                    ContatoDTO contato = new ContatoDTO(null,tNome.getText().toString(),tEmail.getText().toString(),tCelular.getText().toString());
                    DataBase database = new DataBase(MainActivity.this);

                    database.inserir(contato);

                    Toast.makeText(MainActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();
                    tNome.setText("");
                    tEmail.setText("");
                    tCelular.setText("");
                }

            }
        });
    }
}
