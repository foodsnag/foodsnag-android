package me.freets.freetsandroid.adapter;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import me.freets.freetsandroid.R;
import me.freets.freetsandroid.activity.EventActivity;
import me.freets.freetsandroid.activity.SettingsActivity;
import me.freets.freetsandroid.model.Event;
import me.freets.freetsandroid.model.FreetsApi;

/**
 * Created by skroh on 2/21/15.
 */
public class EventListAdapter extends BaseAdapter {

    private List<Event> mDataset;
    private SimpleDateFormat sdf;

    private final EventActivity mActivity;
    private final SharedPreferences mPrefs;

    public EventListAdapter(EventActivity ea, SharedPreferences mPrefs) {
        this.mActivity = ea;
        this.mPrefs = mPrefs;

        List<Event> mock = new ArrayList<Event>();
        mock.add(new Event("HVZ Pizza Party", "Sundial", "RIT", new GregorianCalendar(2015, 2, 28, 13, 0), Event.EventIcon.PIZZA));
        mock.add(new Event("Honors Pizza Friday", "Orange Hall", "RIT", new GregorianCalendar(2015, 2, 22, 0, 30), Event.EventIcon.PIZZA));
        Event e = new Event("e Event", "h Hall", "c College", new GregorianCalendar(2015, 1, 1, 0, 0), Event.EventIcon.BURGERS);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mDataset = mock;

        sdf = new SimpleDateFormat();
        sdf.applyPattern("h:mm a");
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataset.get(position);
    }

    public void remove(Object event) {
        mDataset.remove(event);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_event, parent, false);
        Event event = mDataset.get(position);

        TextView time = (TextView) v.findViewById(R.id.event_time);
        TextView name = (TextView) v.findViewById(R.id.event_name);
        TextView location = (TextView) v.findViewById(R.id.event_location);
        ImageView img = (ImageView) v.findViewById(R.id.event_icon);

        time.setText(sdf.format(event.cal.getTime()));
        name.setText(event.name);
        location.setText(event.location);
        img.setImageResource(event.ic.icid);

        Calendar cal = Calendar.getInstance();
        if(cal.compareTo(event.cal) > 0) {
            time.setTextColor(v.getContext().getResources().getColor(R.color.event_location));
        } else if (cal.get(Calendar.MINUTE) - event.cal.get(Calendar.MINUTE) <= 30)  {
            time.setTypeface(Typeface.DEFAULT_BOLD);
            time.setTextColor(v.getContext().getResources().getColor(R.color.accent));
        }

        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.view_event;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mDataset.isEmpty();
    }

    public void get() {

        int lim = SettingsActivity.FREETS_EVENT_LIMIT;
        String str = mPrefs.getString("pref_location_id", "");
        int id = Integer.valueOf(str);
        FreetsApi api = FreetsApi.getInstance();
        api.getEvents(id, lim, new FreetsApi.EventsCallback() {
            @Override
            public void setEvents(final List<Event> events) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        mDataset.clear();
                        if(events != null) {
                            mDataset.addAll(events);
                        }
                        EventListAdapter.this.notifyDataSetChanged();
                        ListView lv = (ListView)mActivity.findViewById(R.id.event_list_view);
                        lv.forceLayout();
//                        lv.destroyDrawingCache();
//                        lv.forceLayout();
//                        lv.refreshDrawableState();
                        //lv.invalidate();
                    }
                });
                //EventListAdapter.this.notifyDataSetChanged();

                System.out.println("Here");
                Log.d("tag", "here");
                //EventListAdapter.this.notifyDataSetInvalidated();
            }
        });
    }
}
