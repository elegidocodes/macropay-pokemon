package com.elegidocodes.demo.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.elegidocodes.demo.R;
import com.elegidocodes.demo.databinding.PokemonItemBinding;
import com.elegidocodes.demo.model.Pokemon;


public class PokemonAdapter extends PagingDataAdapter<Pokemon, PokemonAdapter.ViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

    private final RequestManager requestManager;

    public PokemonAdapter(@NonNull DiffUtil.ItemCallback<Pokemon> diffCallback, RequestManager requestManager) {
        super(diffCallback);
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PokemonItemBinding movieItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.pokemon_item, parent, false);
        return new ViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        if (pokemon != null) {
            holder.binding.setPokemon(pokemon);
            //holder.bind(pokemon, requestManager);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? LOADING_ITEM : MOVIE_ITEM;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final PokemonItemBinding binding;
        private final ImageView image;

        public ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.image = binding.image;
        }

        /*private void bind(Movie movie, RequestManager requestManager) {
            binding.setMovie(movie);
            requestManager.load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(poster);
        }*/

    }

}

