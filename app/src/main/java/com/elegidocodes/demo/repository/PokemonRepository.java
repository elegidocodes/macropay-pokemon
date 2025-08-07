package com.elegidocodes.demo.repository;

import android.content.Context;

import com.elegidocodes.demo.app.MyAPI;
import com.elegidocodes.demo.app.MyRetrofit;
import com.elegidocodes.demo.model.PokemonDetail;
import com.elegidocodes.demo.model.SpeciesDetail;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import retrofit2.Response;


public class PokemonRepository {

    private final Context context;
    private final MyAPI myAPI;

    public PokemonRepository(Context context){
        this.context = context;
        this.myAPI = MyRetrofit.getService();
    }


    public CompletableFuture<PokemonDetail> getPokemonDetail(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Response<PokemonDetail> response = myAPI.getPokemonDetail(id).execute();
                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException("Failed to fetch PokemonDetail");
                }

                PokemonDetail pokemonDetail = response.body();

                Response<SpeciesDetail> speciesResponse = myAPI.getSpeciesDetail(id).execute();
                if (!speciesResponse.isSuccessful() || speciesResponse.body() == null) {
                    throw new RuntimeException("Failed to fetch SpeciesDetail");
                }

                pokemonDetail.setSpeciesDetail(speciesResponse.body());

                return pokemonDetail;

            } catch (IOException e) {
                throw new RuntimeException("Network error", e);
            }
        });
    }


}
