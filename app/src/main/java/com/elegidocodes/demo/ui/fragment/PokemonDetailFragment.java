package com.elegidocodes.demo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.elegidocodes.demo.R;
import com.elegidocodes.demo.databinding.FragmentPokemonDetailBinding;
import com.elegidocodes.demo.model.Pokemon;
import com.elegidocodes.demo.utility.NumberUtil;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PokemonDetailFragment extends Fragment {

    private final static String TAG = "POKEMON_DETAIL_FRAGMENT";

    @Inject
    RequestManager requestManager;

    private Context context;
    private Activity activity;

    private FragmentPokemonDetailBinding binding;
    private PokemonDetailViewModel viewModel;

    private Pokemon pokemon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_detail, container, false);
        viewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);

        context = requireContext();
        activity = requireActivity();

        if (getArguments() != null) {
            String pokemonJson = getArguments().getString("pokemon");
            pokemon = new Gson().fromJson(pokemonJson, Pokemon.class);

            long id = NumberUtil.getIdFromUrl(pokemon.getUrl());
            viewModel.getPokemonDetail(id)
                    .thenAccept(pokemonDetail -> {
                        activity.runOnUiThread(() -> {
                            requestManager.load(pokemonDetail.getSprites().getFrontDefault()).into(binding.imageView);
                            binding.setPokemonDetail(pokemonDetail);


                            binding.indicator.setVisibility(View.GONE);
                            binding.mainWrapper.setVisibility(View.VISIBLE);
                        });

                    })
                    .exceptionally(throwable -> {
                        activity.runOnUiThread(() -> {
                            Log.e(TAG, "Error", throwable);
                            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            binding.indicator.setVisibility(View.GONE);
                            binding.mainWrapper.setVisibility(View.GONE);
                        });

                        return null;
                    });

        }

        return binding.getRoot();
    }

}