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
        String []titleString={"","",""};
        titleString[0] = cursor.getString(cursor.getColumnIndexOrThrow("first"));
        titleString[1] = cursor.getString(cursor.getColumnIndexOrThrow("second"));
        titleString[2] = cursor.getString(cursor.getColumnIndexOrThrow("third"));
        //check how many items exist
        int numberOfItems=2;
        for(int i=0;i<3;i++){
            if(titleString[i]==null){
                numberOfItems=i;
                break;
            }
        }
        String finalString="";
        for(int i=0;i<numberOfItems+1;i++){
            if(i<numberOfItems){
                finalString+=titleString[i]+", ";
            }else{
                finalString+=titleString[i];
            }
        }
        //String title="dummy";
        title1.setText(finalString);
    }
}
