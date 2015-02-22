package me.freets.freetsandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import me.freets.freetsandroid.R;
import me.freets.freetsandroid.adapter.EventListAdapter;

public class EventActivity extends Activity {

    private ListView mListView;
    private ListAdapter mAdapter;

    //private RecyclerView mRecyclerView;
    //private EventAdapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mListView = (ListView)findViewById(R.id.event_list_view);
        mAdapter = new EventListAdapter();

        mListView.setAdapter(mAdapter);

//        mRecyclerView = (RecyclerView)findViewById(R.id.event_recycler_view);
//        mLayoutManager = new LinearLayoutManager(this);
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new EventAdapter();
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
