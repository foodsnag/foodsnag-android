package me.freets.freetsandroid.adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import me.freets.freetsandroid.R;
import me.freets.freetsandroid.model.Event;

/**
 * Created by skroh on 2/21/15.
 */
public class EventListAdapter implements ListAdapter {

    private List<Event> mDataset;
    private SimpleDateFormat sdf;

    public EventListAdapter() {
        List<Event> mock = new ArrayList<Event>();
        mock.add(new Event("HVZ Pizza Party", "Sundial", "RIT", new GregorianCalendar(2015, 2, 28, 13, 0)));
        mock.add(new Event("Honors Pizza Friday", "Orange Hall", "RIT", new GregorianCalendar(2015, 2, 27, 12, 0)));
        Event e = new Event("e Event", "h Hall", "c College", new GregorianCalendar(2015, 1, 1, 0, 0));
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

        time.setText(sdf.format(event.cal.getTime()));
        name.setText(event.name);
        location.setText(event.location);

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
        return false;
    }
}
