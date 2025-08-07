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
import com.elegidocodes.demo.utility.NumberUtil;


public class PokemonAdapter extends PagingDataAdapter<Pokemon, PokemonAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon);
    }

    private final RequestManager requestManager;
    private final OnItemClickListener onItemClickListener;

    public PokemonAdapter(@NonNull DiffUtil.ItemCallback<Pokemon> diffCallback,
                          RequestManager requestManager,
                          OnItemClickListener listener) {
        super(diffCallback);
        this.requestManager = requestManager;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PokemonItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.pokemon_item, parent, false);
        return new ViewHolder(binding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        if (pokemon != null) {
            holder.binding.setPokemon(pokemon);
            holder.bind(pokemon, requestManager);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;
        private final ImageView image;

        public ViewHolder(PokemonItemBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.image = binding.image;

            binding.getRoot().setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(binding.getPokemon());
                }
            });
        }

        private void bind(Pokemon pokemon, RequestManager requestManager) {
            long id = NumberUtil.getIdFromUrl(pokemon.getUrl());
            String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";

            requestManager.load(imageUrl).into(image);
        }
    }
}


