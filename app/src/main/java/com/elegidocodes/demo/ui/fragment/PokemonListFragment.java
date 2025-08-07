package com.elegidocodes.demo.ui.fragment;

import static android.view.View.GONE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.RequestManager;
import com.elegidocodes.demo.R;
import com.elegidocodes.demo.adapter.PokemonAdapter;
import com.elegidocodes.demo.adapter.PokemonComparator;
import com.elegidocodes.demo.adapter.PokemonLoadStateAdapter;
import com.elegidocodes.demo.databinding.FragmentPokemonListBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Unit;

@AndroidEntryPoint
public class PokemonListFragment extends Fragment {

    @Inject
    RequestManager requestManager;

    private FragmentPokemonListBinding binding;
    private PokemonListViewModel viewmodel;
    private PokemonAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CircularProgressIndicator circularProgressIndicator;
    private TextView message;

    private Disposable disposable;

    private Context context;

    public static PokemonListFragment newInstance() {
        return new PokemonListFragment();
    }

    private void bindViews() {
        swipeRefreshLayout = binding.swipeRefreshLayout;
        recyclerView = binding.recyclerView;
        circularProgressIndicator = binding.circularProgressIndicator;
        message = binding.message;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_list, container, false);

        context = requireContext();

        viewmodel = new ViewModelProvider(this).get(PokemonListViewModel.class);
        adapter = new PokemonAdapter(new PokemonComparator(), requestManager);
        adapter.withLoadStateHeader(new PokemonLoadStateAdapter(v -> adapter.retry()));

        bindViews();

        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(adapter);

        disposable = subscribe();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.refresh();
        });

        adapter.addLoadStateListener(loadStates -> {
            LoadState refreshState = loadStates.getRefresh();
            if (refreshState instanceof LoadState.NotLoading || refreshState instanceof LoadState.Error) {
                swipeRefreshLayout.setRefreshing(false);
            }
            return Unit.INSTANCE;
        });

        return binding.getRoot();
    }

    private Disposable subscribe() {
        return viewmodel.getPagingDataFlowable().subscribe(
                pagingData -> {
                    adapter.submitData(getLifecycle(), pagingData);
                    circularProgressIndicator.setVisibility(GONE);
                },
                throwable -> {
                    message.setText(throwable.getMessage());
                    message.setVisibility(View.VISIBLE);
                    circularProgressIndicator.setVisibility(GONE);
                }
        );
    }

    @Override
    public void onDestroyView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroyView();
    }

}