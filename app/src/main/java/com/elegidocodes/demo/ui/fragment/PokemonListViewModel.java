package com.elegidocodes.demo.ui.fragment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.elegidocodes.demo.model.Pokemon;
import com.elegidocodes.demo.repository.PokemonPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class PokemonListViewModel extends ViewModel {

    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = PAGE_SIZE / 2;
    private static final int INITIAL_LOAD_SIZE = PAGE_SIZE * 3;
    private static final int MAX_CACHE_SIZE = PAGE_SIZE * 5;

    private final Flowable<PagingData<Pokemon>> pagingDataFlowable;
    private final CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

    public PokemonListViewModel() {
        Pager<Integer, Pokemon> pager = createPager();
        pagingDataFlowable = PagingRx.getFlowable(pager)
                .compose(flowable -> PagingRx.cachedIn(flowable, viewModelScope));
    }

    private Pager<Integer, Pokemon> createPager() {
        return new Pager<>(
                new PagingConfig(
                        PAGE_SIZE,
                        PREFETCH_DISTANCE,
                        false,
                        INITIAL_LOAD_SIZE,
                        MAX_CACHE_SIZE
                ),
                this::createPagingSource
        );
    }

    private PokemonPagingSource createPagingSource() {
        return new PokemonPagingSource();
    }

    public Flowable<PagingData<Pokemon>> getPagingDataFlowable() {
        return pagingDataFlowable;
    }

    public Flowable<PagingData<Pokemon>> getPagingDataWithErrorHandling() {
        return pagingDataFlowable
                .onErrorResumeNext(throwable -> {
                    return Flowable.empty();
                });
    }

}