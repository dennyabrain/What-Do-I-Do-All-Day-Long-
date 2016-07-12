package space.dennymades.whatdoido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by abrain on 7/3/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbAppWDID";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_ENTRIES = "entries";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ITEM_ID = "item_id";

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEM_ID2 = "item_id";
    public static final String COLUMN_ITEM="item";
    public static final String COLUMN_RANK="rank";

    private static final String TABLE_ENTRY_CREATE = "" +
            "create table entries(item_id integer primary key autoincrement," +
            "date text)";

    private static final String TABLE_ITEM_CREATE="" +
            "create table items(id integer primary key autoincrement," +
            "item_id integer, item text, rank integer)";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL(TABLE_ENTRY_CREATE);
        }catch(Throwable e){
            Log.d("denny","entry table fucked up");
            e.printStackTrace();
        }
        try{
            db.execSQL(TABLE_ITEM_CREATE);
        }catch(Throwable e){
            Log.d("denny","item table fucked up");
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists entries");
        db.execSQL("drop table if exists items");
        onCreate(db);
    }
}
