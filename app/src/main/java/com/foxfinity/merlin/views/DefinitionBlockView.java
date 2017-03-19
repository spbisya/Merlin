package com.foxfinity.merlin.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foxfinity.merlin.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class DefinitionBlockView extends RelativeLayout {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;

    public DefinitionBlockView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_definition_block, this, true);
        ButterKnife.bind(this);
    }

    public DefinitionBlockView withDefinitions(String partOfSpeech, List<String> definitions) {
        String caption = partOfSpeech.substring(0, 1).toUpperCase() + partOfSpeech.substring(1);
        title.setText(caption);
        description.setText(getStringFromList(definitions));
        return this;
    }

    private String getStringFromList(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            stringBuilder.append("").append(i + 1).append(". ").append(strings.get(i)).append("\n\n");
        }
        return stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n\n"));
    }
}
