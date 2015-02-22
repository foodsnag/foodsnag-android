package me.freets.freetsandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    private List<Event> mDataset;
    private SimpleDateFormat sdf;

    public EventAdapter() {
        List<Event> mock = new ArrayList<Event>();
        mock.add(new Event("HVZ Pizza Party", "Sundial", "RIT", new GregorianCalendar(2015, 2, 28, 13, 0)));
        mock.add(new Event("Honors Pizza Friday", "Orange Hall", "RIT", new GregorianCalendar(2015, 2, 27, 12, 0)));
        Event e = new Event("e Event", "h Hall", "c College", new GregorianCalendar(2015, 1, 1, 0, 0));
        mock.add(e);
        mock.add(e);
        mock.add(e);
        mDataset = mock;

        sdf = new SimpleDateFormat();
        sdf.applyPattern("h:mm a");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_event, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView time = (TextView) holder.mView.findViewById(R.id.event_time);
        TextView name = (TextView) holder.mView.findViewById(R.id.event_name);

        time.setText(sdf.format(mDataset.get(position).cal.getTime()));
        name.setText(mDataset.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
