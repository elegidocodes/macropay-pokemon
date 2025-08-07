package com.elegidocodes.demo.ui.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.elegidocodes.demo.model.PokemonDetail;
import com.elegidocodes.demo.repository.PokemonRepository;

import java.util.concurrent.CompletableFuture;

public class PokemonDetailViewModel extends AndroidViewModel {

    private final PokemonRepository pokemonRepository;

    public PokemonDetailViewModel(@NonNull Application application) {
        super(application);
        this.pokemonRepository = new PokemonRepository(application);
    }

    public CompletableFuture<PokemonDetail> getPokemonDetail(long id) {
        return pokemonRepository.getPokemonDetail(id);
    }

}