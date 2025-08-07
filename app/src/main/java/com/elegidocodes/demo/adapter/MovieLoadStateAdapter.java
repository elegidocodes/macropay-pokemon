package com.elegidocodes.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.elegidocodes.demo.R;
import com.elegidocodes.demo.databinding.LoadStateItemBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;


public class MovieLoadStateAdapter extends LoadStateAdapter<MovieLoadStateAdapter.ViewModel> {

    private final View.OnClickListener retry;

    public MovieLoadStateAdapter(View.OnClickListener retry) {
        this.retry = retry;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, @NonNull LoadState loadState) {
        viewModel.bind(loadState);
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        return new ViewModel(viewGroup, retry);
    }

    static class ViewModel extends RecyclerView.ViewHolder {

        private final LoadStateItemBinding binding;
        private final MaterialButton btnRetry;
        private final CircularProgressIndicator indicator;
        private final TextView message;

        public ViewModel(ViewGroup parent, View.OnClickListener retry) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));
            this.binding = LoadStateItemBinding.bind(itemView);
            btnRetry = binding.btnRetry;
            indicator = binding.circularProgressIndicator;
            message = binding.message;

            btnRetry.setOnClickListener(retry);
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error error = (LoadState.Error) loadState;
                message.setText(error.getError().getMessage());
            }

            indicator.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            btnRetry.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            message.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }

    }

}
