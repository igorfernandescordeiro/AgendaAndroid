package com.example.ifcig.agendaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import persistencia.ContatoDTO;
import persistencia.DataBase;
import persistencia.UsuarioAdapter;

public class ContatosActivity extends AppCompatActivity {
    private ListView lista;
    private ImageButton bEditar, bExcluir;

    @Override
    protected void onResume() {
        super.onResume();
        this.carregarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        bEditar = (ImageButton) findViewById(R.id.imageButtonEditar);
        bExcluir = (ImageButton) findViewById(R.id.imageButtonExcluir);
        lista = (ListView) findViewById(R.id.listViewLista);

        //this.carregarLista();

        bEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContatosActivity.this, TelaEditarActivity.class);
                startActivity(i);
            }
        });

        bExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void carregarLista(){
        List<ContatoDTO> list = new ArrayList<>();

        DataBase database = new DataBase(ContatosActivity.this);
        list = database.buscar();

        lista.setAdapter(new UsuarioAdapter(this, list));
    }
}
