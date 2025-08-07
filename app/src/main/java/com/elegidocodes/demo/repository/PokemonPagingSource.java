package com.elegidocodes.demo.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.elegidocodes.demo.app.MyRetrofit;
import com.elegidocodes.demo.model.Pokemon;
import com.elegidocodes.demo.model.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonPagingSource extends RxPagingSource<Integer, Pokemon> {

    private static final int PAGE_SIZE = 20;
    private static final int STARTING_OFFSET = 0;

    @NonNull
    @Override
    public Single<PagingSource.LoadResult<Integer, Pokemon>> loadSingle(@NonNull PagingSource.LoadParams<Integer> params) {
        int offset = params.getKey() != null ? params.getKey() : STARTING_OFFSET;

        return MyRetrofit.getService()
                .getPokemonList(offset, params.getLoadSize())
                .subscribeOn(Schedulers.io())
                .map(response -> toLoadResult(response, offset, params.getLoadSize()))
                .onErrorReturn(PagingSource.LoadResult.Error::new);
    }

    private PagingSource.LoadResult<Integer, Pokemon> toLoadResult(
            Result response, int offset, int pageSize) {

        List<Pokemon> data = response.getResults();
        int totalCount = response.getCount();

        Integer nextKey = (offset + pageSize < totalCount) ? offset + pageSize : null;
        Integer prevKey = (offset - pageSize >= 0) ? offset - pageSize : null;

        int itemsBefore = offset;
        int itemsAfter = Math.max(totalCount - offset - data.size(), 0);

        return new PagingSource.LoadResult.Page<>(
                data,
                prevKey,
                nextKey,
                itemsBefore,
                itemsAfter
        );
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Pokemon> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) return null;

        PagingSource.LoadResult.Page<Integer, Pokemon> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) return null;

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) return prevKey + PAGE_SIZE;

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) return nextKey - PAGE_SIZE;

        return null;
    }

}

