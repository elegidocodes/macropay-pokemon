package com.elegidocodes.demo.app;

import com.elegidocodes.demo.model.PokemonDetail;
import com.elegidocodes.demo.model.Result;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPI {

    @GET("pokemon")
    Single<Result> getPokemonList(
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    @GET("pokemon/{id}")
    Call<PokemonDetail> getPokemonDetail(@Path("id") long id);

}
