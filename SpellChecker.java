import DictionaryTypes.*;

import java.util.ArrayList;

/**
 * A class that represents a spell checker for any given word.
 */
public class SpellChecker {

    /**
     * The Spell Checker as an interface for dictionary types such as ArrayDictionary, ResizingArrayDictionary and TrieDictionary.
     */
    DictionaryInterface spellChecker;

    /**
     * Creates the spell checker based on the type of dictionary provided.
     * @param checkerType
     *     The type of dictionary of words that will be used by the spell checker.
     *     Possible Values: Trie, Array, ResizingArray.
     */
    public SpellChecker(String checkerType) {
        if (checkerType.equals("Trie")) {
            spellChecker = new TrieDictionary();
        } else if (checkerType.equals("Array")) {
            spellChecker = new ArrayDictionary();
        } else if (checkerType.equals("ResizingArray")) {
            spellChecker = new ResizingArrayDictionary();
        } else {
            System.out.println("Invalid command line input, use one of: Trie, Array, or ResizingArray");
            System.exit(0);
        }
    }

    /**
     * A method that determines whether a given String named word is spelt correctly or exists in the current dictionary instance.
     * @param word
     *     A String that is checked to determine whether it exists in the current dictionary instance and is spelt correctly.
     * @return
     *     A boolean that describes whether the String exists in the current dictionary instance and is spelt correctly.
     */
    public boolean check(String word) {
        return spellChecker.isWord(word);
    }

    /**
     * A method that returns the closest word in the current dictionary instance to the given word in terms of levenshtein distance.
     * @param wordToCheck
     *     The String to be compared to the current dictionary instance to determine the closest word in the dictionary.
     * @return
     *     The closest word as a String in the current dictionary instance to the given word in terms of levenshtein distance.
     */
    public String getBestSuggestion(String wordToCheck) {

        ArrayList<String> wordList = spellChecker.getDictionaryWords();
        String[] result = spellChecker.getTopNSuggestions(wordList, wordToCheck, 1);
        if (result.length > 0) {
            return result[0];
        }
        else return "";

    }
}
