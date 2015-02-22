package me.freets.freetsandroid.model;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by skroh on 2/22/15.
 */
public interface FreetsService  {
    @GET("/locations")
    void listLocations(Callback<List<JsonLocation>> cb);

    @GET("/location/{id}/events/{lim}")
    void listEvents(@Path("id") int id, @Path("lim") int lim, Callback<List<JsonEvent>> cb);
}
