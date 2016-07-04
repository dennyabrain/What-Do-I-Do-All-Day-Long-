package space.dennymades.whatdoido;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abrain on 7/3/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String dbName, int dbVersion){
        super(context, dbName, null, dbVersion);
    }
}
