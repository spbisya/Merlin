package com.foxfinity.merlin.models;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Project Merlin. Created by gwa on 3/18/17.
 */

public class Word {
    String word;
    Integer score;
    @Nullable
    List<String> tags;
    @Nullable
    Integer numSyllables;

    public String getWord() {
        return word;
    }

    public Integer getScore() {
        return score;
    }

    @Nullable
    public List<String> getTags() {
        return tags;
    }

    @Nullable
    public Integer getNumSyllables() {
        return numSyllables;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", score=" + score +
                ", tags=" + tags +
                ", numSyllables=" + numSyllables +
                '}';
    }
}
