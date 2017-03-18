package com.foxfinity.merlin.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.models.Word;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> words;

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WordViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false));

    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.text.setText(words.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.word)
        TextView text;

        public WordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
