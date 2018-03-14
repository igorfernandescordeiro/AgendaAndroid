package persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ifcig on 04/05/2017.
 */

public class DatabaseCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "agendaAndroid";
    private static final int VERSAO_BD = 1;

    public DatabaseCore(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS contato(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, email TEXT NOT NULL, celular TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if((oldVersion < newVersion) && newVersion < VERSAO_BD){
            db.execSQL("DROP TABLE contato;");
            onCreate(db);
        }

    }
}
