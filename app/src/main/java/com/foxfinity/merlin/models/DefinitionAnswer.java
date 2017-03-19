package com.foxfinity.merlin.models;

import java.util.List;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class DefinitionAnswer {
    String word;
    List<Definition> definitions;

    public String getWord() {
        return word;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public static class Definition{
        String definition;
        String partOfSpeech;

        public String getDefinition() {
            return definition;
        }

        public String getPartOfSpeech() {
            return partOfSpeech;
        }
    }
}
