package com.example.flixapp_allfeatures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixapp_allfeatures.adapters.MovieAdapter;
import com.example.flixapp_allfeatures.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String urlLink ="https://api.themoviedb.org/3/movie/now_playing?api_key=af5086ead55b1fc08ec9f072bf15cad9";
    public static final String tag ="MainActivity";

    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rvMovies = findViewById(R.id.rvMoviesList);
        movies = new ArrayList<>();

        //Create adapter
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        //Set adapter on recycler view
        rvMovies.setAdapter(movieAdapter);
        //Set a layout manager on recycler view - needed for recycler view to know how to layout the different views onto the screen
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlLink, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(tag, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                //As you are trying to parse through this, it may not exist.
                try {
                    //Want to take JSONArray and turn it into a list of movies, move that logic into Movie.java
                    JSONArray results = jsonObject.getJSONArray("results");
                    //info level
                    Log.i(tag, "Results:" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(tag, "Movies:" + movies.size());
                } catch (JSONException e) {
                    Log.e(tag, "Json exception", e);
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(tag, "onFailure");
            }
        });
    }
}