package com.foxfinity.merlin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.definition.DefinitionActivity;
import com.foxfinity.merlin.models.Word;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> words;
    private Context context;

    public WordAdapter(Context context, List<Word> words) {
        this.words = words;
        this.context = context;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WordViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false));

    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.bind(position, words.get(position), (position1, word) -> {
            //Open definitions activity
            DefinitionActivity.display(context, word.getWord());
        });
    }

    public void changeWords(List<Word> words) {
        this.words.clear();
        this.words.addAll(words);
        notifyDataSetChanged();
    }

    public void clearList() {
        this.words.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.word)
        TextView text;

        private int position;
        private Word word;
        private Callback callback;

        WordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            text.setOnClickListener(v -> {
                if (callback != null) {
                    callback.clicks(position, word);
                }
            });
        }

        void bind(int position, Word word, Callback callback) {
            this.position = position;
            this.word = word;
            this.callback = callback;
            text.setText(word.getWord());
        }

        public interface Callback {
            void clicks(int position, Word word);
        }
    }
}
