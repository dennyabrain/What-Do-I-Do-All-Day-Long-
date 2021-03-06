package space.dennymades.whatdoido;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by abrain on 7/4/16.
 */
public class EntryDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public EntryDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    //Create an Entry : this will have the date string and item id. The item object itself will be empty
    public long insertEntry(){
        Calendar now = Calendar.getInstance();
        String month = now.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.US);
        int date = now.get(Calendar.DATE);

        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_DATE,""+month+" "+String.valueOf(date));
        long insertId = db.insert(dbHelper.TABLE_ENTRIES, null, values);
        Log.d("denny", "inserted new"+String.valueOf(insertId));

        return insertId;
    }

    //Update the current entry : this function will add an item to the current entry's corresponding item table
    // the function computes the gets the current entry's id and add the new item to the corresponding id in the item table
    public void insertItemWithCurrentID(int item_id, String itemName, int rank){
        //perform actions to find out existing items


        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_ITEM_ID2, item_id);
        values.put(dbHelper.COLUMN_ITEM, itemName);
        values.put(dbHelper.COLUMN_RANK,rank);
        try{
            long insertId = db.insert(dbHelper.TABLE_ITEMS, null, values);
            Log.d("denny", "inserted new item "+String.valueOf(insertId));
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    //Update order number : this function will change the order of the entry item. TODO

    //List all entries : this will spit out all the entries in a format that is acceptable to the adapter
    public Cursor getAllItems(){
        //Cursor cur;
        /*final String query="SELECT items.id as '_id', entries.item_id, date, item, rank"+
                " from "+DBHelper.TABLE_ENTRIES+
                " inner join "+DBHelper.TABLE_ITEMS+
                " on entries.item_id=items.item_id";*/
        final String query="select\n" +
                "item_id as _id,\n" +
                "max(case when rank=1 then item end) as first,\n" +
                "max(case when rank=2 then item end) as second,\n" +
                "max(case when rank=3 then item end) as third\n" +
                "from items\n" +
                "group by item_id;";
        return db.rawQuery(query,null);
    }
}
