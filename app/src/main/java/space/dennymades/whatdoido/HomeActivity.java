package space.dennymades.whatdoido;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements NewItemDialogFragment.NewItemDialogListener {
    private EntryDAO entry;
    private Toolbar toolbar;
    private NewItemDialogFragment itemDialog;
    FragmentManager fm = getSupportFragmentManager();
   // SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        entry = new EntryDAO(this);
        entry.open();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.newCard:
                Log.d("denny", "adding new card");
                long entryId=entry.insertEntry();
                SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
                Editor edit = pref.edit();
                edit.putInt("currentRank",0);
                edit.putInt("currentId",(int)entryId);
                edit.apply();
                Log.d("denny","inserted entry with id"+entryId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void BtnAddItem(View v){
        Log.d("denny", "FAB Clicked");
        itemDialog = new NewItemDialogFragment();
        itemDialog.show(getFragmentManager(), "NewItemDialogFragment");
    }

    //Implement listeners for clicks on dialog
    @Override
    public void onNewItemDialogPositiveClick(DialogFragment dialog){
        Dialog dialogView = dialog.getDialog();
        Log.d("denny", "FAB Clicked");
        EditText editText = (EditText) dialogView.findViewById(R.id.username);
        String currentItemString = editText.getText().toString();
        Log.d("denny", currentItemString);
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        //Editor edit = pref.edit();
        int currentRank = pref.getInt("currentRank",-1);
        int currentId = pref.getInt("currentId",-1);
        Log.d("denny","fetching entry's current id "+currentId);
        Log.d("denny","will insert item with current rank "+currentRank);
        if(currentRank<3){
            //insert item in database and show on screen
            entry.insertItemWithCurrentID(currentId,currentItemString,currentRank+1);
            Editor edit = pref.edit();
            currentRank++;
            edit.putInt("currentRank", currentRank);
            edit.apply();
            Log.d("denny","currentRank updated to "+(currentRank));
        }else{
            Toast t = Toast.makeText(this,"only 3 items allowed",Toast.LENGTH_SHORT);
            t.show();
        }
    }

    @Override
    public void onNewItemDialogNegativeClick(DialogFragment dialog){

    }
}