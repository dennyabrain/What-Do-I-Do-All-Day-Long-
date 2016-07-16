package space.dennymades.whatdoido;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by abrain on 7/16/16.
 */
public class TimelineAdapter extends CursorAdapter {
    public TimelineAdapter(Context context, Cursor cursor, int flags){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView title1 = (TextView) view.findViewById(R.id.text1);
        TextView title2 = (TextView) view.findViewById(R.id.text2);
        TextView title3 = (TextView) view.findViewById(R.id.text3);
        TextView title4 = (TextView) view.findViewById(R.id.text4);

        String title = cursor.getString(cursor.getColumnIndexOrThrow("item"));
        //String title="dummy";
        title1.setText(title);
    }
}
