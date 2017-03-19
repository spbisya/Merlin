package com.foxfinity.merlin.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Merlin. Created by gwa on 3/18/17.
 */

public class Word implements Parcelable {
    String word;
    Integer score;
    @Nullable
    List<String> tags;
    @Nullable
    Integer numSyllables;
    @Nullable
    List<String> defs;

    @Nullable
    public List<String> getDefs() {
        return defs;
    }

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
                ", defs=" + defs +
                '}';
    }

    protected Word(Parcel in) {
        word = in.readString();
        score = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<String>();
            in.readList(tags, String.class.getClassLoader());
        } else {
            tags = null;
        }
        numSyllables = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            defs = new ArrayList<String>();
            in.readList(defs, String.class.getClassLoader());
        } else {
            defs = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        if (score == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(score);
        }
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
        }
        if (numSyllables == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(numSyllables);
        }
        if (defs == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(defs);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}