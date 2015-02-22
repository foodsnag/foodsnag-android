package me.freets.freetsandroid.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by skroh on 2/22/15.
 */
public class FreetsApi {
    private static FreetsApi singleton = null;
    public static FreetsApi getInstance() {
        if(singleton == null) {
            singleton = new FreetsApi();
        }
        return singleton;
    }

    private RestAdapter mAdapter;
    private FreetsService mService;

    public FreetsApi()
    {
        Gson g = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

        mAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint("http://freets.me/api").setConverter(new GsonConverter(g)).build();
        mService = mAdapter.create(FreetsService.class);
    }

    public void getLocations(final LocationsCallback cb) {
        mService.listLocations(new Callback<List<JsonLocation>>() {
            @Override
            public void success(List<JsonLocation> jsonLocations, Response response) {
                List<Location> locations = new ArrayList<Location>();
                for(JsonLocation jsl : jsonLocations) {
                    locations.add(new Location(jsl));
                }
                cb.setLocations(locations);
            }

            @Override
            public void failure(RetrofitError error) {
                cb.setLocations(null);
            }
        });
    }

    public void getEvents(final int id, int lim, final EventsCallback cb) {
        mService.listEvents(id, lim, new Callback<List<JsonEvent>>() {
            @Override
            public void success(List<JsonEvent> jsonEvents, Response response) {
                List<Event> events = new ArrayList<Event>();
                for(JsonEvent jse : jsonEvents) {
                    events.add(new Event(jse));
                }
                cb.setEvents(events);
            }

            @Override
            public void failure(RetrofitError error) {
                Object o = error.getBody();
                cb.setEvents(null);
            }
        });
    }

    public interface LocationsCallback {
        public void setLocations(List<Location> locations);
    }

    public interface EventsCallback {
        public void setEvents(final List<Event> events);
    }
}
