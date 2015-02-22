package me.freets.freetsandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.swipedismiss.SwipeDismissListViewTouchListener;

import me.freets.freetsandroid.R;
import me.freets.freetsandroid.adapter.EventListAdapter;

public class EventActivity extends Activity {

    private ListView mListView;
    private EventListAdapter mAdapter;

    private Menu menu;
    private MenuItem mToggleNotify;
    public static boolean notifyOn;

    private SwipeRefreshLayout mRefresher;

    //private RecyclerView mRecyclerView;
    //private EventAdapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        mListView = (ListView)findViewById(R.id.event_list_view);
        mAdapter = new EventListAdapter(EventActivity.this, sharedPref);

        mListView.setAdapter(mAdapter);

        // Create a ListView-specific touch listener. ListViews are given special treatment because
        // by default they handle touches for their list items... i.e. they're in charge of drawing
        // the pressed state (the list selector), handling list item clicks, etc.
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        mListView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mAdapter.remove(mAdapter.getItem(position));
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        mListView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        mListView.setOnScrollListener(touchListener.makeScrollListener());

        //final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("pref_notify") && mToggleNotify != null) {
                    if(sharedPref.getBoolean("pref_notify", true)) {
                        mToggleNotify.setIcon(R.drawable.ic_sync_white_36dp);
                    } else {
                        mToggleNotify.setIcon(R.drawable.ic_sync_disabled_white_36dp);
                    }
                }
            }
        });

        mRefresher = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.get();
                mRefresher.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        mToggleNotify = menu.findItem(R.id.action_enable_notify);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPref.getBoolean("pref_notify", true)) {
            mToggleNotify.setIcon(R.drawable.ic_sync_white_36dp);
        } else {
            mToggleNotify.setIcon(R.drawable.ic_sync_disabled_white_36dp);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                return true;
            case R.id.action_enable_notify:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
