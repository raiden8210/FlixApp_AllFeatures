package com.example.flixapp_allfeatures;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixapp_allfeatures.databinding.ActivityMovieDetailsBinding;
import com.example.flixapp_allfeatures.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    private ActivityMovieDetailsBinding binding;
    String videoId;
    View tvOverview;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_movie_trailer);
        tvOverview = findViewById(R.id.tvOverview);



        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        String imbd= String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=af5086ead55b1fc08ec9f072bf15cad9&language=en-US", id);
        String youtube_key = "AIzaSyCvu3oRVU80zry-_dTr3qxMVCQOypSfyyc";

        //videoId = "tKodtNFpzBA";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(imbd, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject obj = results.getJSONObject(0);
                    videoId = obj.getString("key");
                    Log.i("MovieTrailerActivity", "yesyeyesses");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });

        YouTubePlayerView playerView = findViewById(R.id.player);

        playerView.initialize((youtube_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                Log.i("MovieTrailerActivity", videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });





    }
}
