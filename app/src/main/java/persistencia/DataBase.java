package persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.ifcig.agendaandroid.MainActivity;
import com.example.ifcig.agendaandroid.TelaEditarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ifcig on 04/05/2017.
 */

public class DataBase {
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase bd;

    public DataBase() {
    }

    public DataBase(Context context){
        DatabaseCore auxBd = new DatabaseCore(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(ContatoDTO contato){
        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("email", contato.getEmail());
        dados.put("celular", contato.getCelular());

        bd.insert("contato", null, dados);
    }


    public void atualizar(ContatoDTO contato){
        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("email", contato.getEmail());
        dados.put("celular", contato.getCelular());

        bd.update("contato", dados, "id = ?", new String[]{""+contato.getId().toString()});
    }


    public void deletar(ContatoDTO contato){
        bd.delete("contato", "id = "+contato.getId(), null);
    }


    public List<ContatoDTO> buscar(){
        List<ContatoDTO> list = new ArrayList<ContatoDTO>();
        String[] colunas = new String[]{"id", "nome", "email", "celular"};

        Cursor cursor = bd.query("contato", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                ContatoDTO u = new ContatoDTO();
                u.setId(cursor.getInt(0));
                u.setNome(cursor.getString(1));
                u.setEmail(cursor.getString(2));
                u.setCelular(cursor.getString(3));
                list.add(u);

            }while(cursor.moveToNext());
        }

        return(list);
    }
}
