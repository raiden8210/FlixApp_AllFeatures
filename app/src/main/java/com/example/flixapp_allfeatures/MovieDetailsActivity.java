package com.example.flixapp_allfeatures;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixapp_allfeatures.databinding.ActivityMovieDetailsBinding;
import com.example.flixapp_allfeatures.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

import static java.util.Collections.addAll;

public class MovieDetailsActivity extends AppCompatActivity {
    Movie movie;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    String id;
    ImageView ivBackdrop;
    Button btnPlay;
    String ykey;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String imbd= String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=af5086ead55b1fc08ec9f072bf15cad9&language=en-US", id);
        String youtube_key = "AIzaSyCvu3oRVU80zry-_dTr3qxMVCQOypSfyyc";

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        ivBackdrop = findViewById(R.id.ivBackdrop);
        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        id = movie.getId();
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage >0 ? voteAverage /2.0f : voteAverage);


        LayerDrawable stars = (LayerDrawable) rbVoteAverage.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);


        Glide.with(this).load(movie.getBackdropPath()).placeholder(R.drawable.flicks_backdrop_placeholder).into(ivBackdrop);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(imbd, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject c = json.jsonObject;
                try {
                    JSONArray results = c.getJSONArray("results");
                    JSONObject obj = results.getJSONObject(0);
                    ykey = obj.getString("key");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });

        ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playVid.itemView.find(R.drawable.ivPoster);
                //playerView.initialize(key, playerView);
                //Toast.makeText(getBaseContext(), "hello", Toast.LENGTH_LONG).show();

                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                i.putExtra("id", id);
                startActivity(i);

            }
        });



    }
}
