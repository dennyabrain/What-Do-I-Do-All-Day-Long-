package space.dennymades.whatdoido;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

public class Timeline extends AppCompatActivity {
    private EntryDAO entry;
    private Toolbar toolbar;
    private ListView timelineListView;
    private TimelineAdapter timelineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        entry = new EntryDAO(this);
        entry.open();

        timelineListView = (ListView) findViewById(R.id.timelineListView);
        Cursor items = entry.getAllItems();
        timelineAdapter = new TimelineAdapter(this, items, 0);
        timelineListView.setAdapter(timelineAdapter);
    }
}
