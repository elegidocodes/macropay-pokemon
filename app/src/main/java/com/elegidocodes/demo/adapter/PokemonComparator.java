package com.elegidocodes.demo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.elegidocodes.demo.model.Pokemon;

public class PokemonComparator extends DiffUtil.ItemCallback<Pokemon> {

    @Override
    public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
        return oldItem.getUrl().equals(newItem.getUrl());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

}
