package com.example.flixapp_allfeatures.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

//Pojo, plain old java object

@Parcel
public class Movie {

    String posterPath;
    String title;
    String overview;
    String backdropPath;
    Double voteAverage;
    Integer id;

    //Constructor to construct movie object
    //Exception throws JSONException, whoever makes this call is then responsible for the exception

    public Movie(){};

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        voteAverage = jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");

    }

    //Iterating through the JSONArray and constructing a movie for each element in the JSONArray
    public static List<Movie> fromJsonArray(JSONArray JsonMovieArray) throws JSONException {
        List <Movie> movies = new ArrayList<>();

        for(int i = 0; i < JsonMovieArray.length(); i++){
            movies.add(new Movie(JsonMovieArray.getJSONObject(i)));
        }
        //List of movies being returned
        return movies;

    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}
