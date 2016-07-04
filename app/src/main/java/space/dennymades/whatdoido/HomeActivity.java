package space.dennymades.whatdoido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private EntryDAO entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        entry = new EntryDAO(this);
        entry.open();

        entry.insertEntry();
    }
}
