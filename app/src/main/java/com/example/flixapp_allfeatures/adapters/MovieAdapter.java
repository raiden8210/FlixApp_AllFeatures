package com.example.flixapp_allfeatures.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixapp_allfeatures.R;
import com.example.flixapp_allfeatures.models.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
//Initial error after extending Recycler view because there are methods that we need to fill out

    //Need context to inflate a view, where the adapter is coming from
    Context context;
    //Need the actual data, the actual movies that the adapter needs to hold on to.
    List<Movie> movies;

    //These will pass through the constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @NotNull
    @Override
    //Inflate layout (item_movie.xml) return it inside of a viewholder
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Populating data into a view through a viewholder
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //Get the movie at the position
        Movie movie = movies.get(position);
        //Bind the movie data into the viewholder
        holder.bind(movie);
    }

    //Returns total count of items in a list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //ViewHolder is a representation of the row in the RecyclerView. That's what the view passed in will be
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Need to define variables from each thing in the view in order to bind everything
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //In constructor here, can define where TextViews and ImageViews are coming from
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);


        }
    }

}
