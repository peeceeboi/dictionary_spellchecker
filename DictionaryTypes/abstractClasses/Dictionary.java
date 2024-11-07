package DictionaryTypes.abstractClasses;

import java.util.ArrayList;

/**
 * An abstract class that is extended by dictionary-instances such as ArrayTypeDictionary and TrieDictionary.
 */
public abstract class Dictionary {

    /**
     * A method that returns the top N closest words to the passed String named word in terms of the levenshtein distance between them.
     * @param dictionaryWords
     *     An ArrayList of String that will be searched for the top N closest Strings to the passed String named word.
     * @param word
     *     The String that will be compared to the ArrayList of Strings to find the top N closest words to this String.
     * @param N
     *     The maximum number of words to be returned in the result-ArrayList.
     * @return
     *     An ArrayList containing a maximum of N words that are ordered from closest to the passed String named word to the furthest in terms of levenshtein distance.
     */
    public String[] getTopNSuggestions(ArrayList<String> dictionaryWords, String word, int N) {
        // todo
        int[] levDistances = new int[dictionaryWords.size()];
        String[] dicWordsArrayCopy = dictionaryWords.toArray(new String[dictionaryWords.size()]);

        if (dicWordsArrayCopy.length < N) {
            N = dicWordsArrayCopy.length;
        }

        for (int i = 0; i < dictionaryWords.size(); i++) {
            String dicWord = dictionaryWords.get(i);
            levDistances[i] = getLevenshteinDistance(word, dicWord);
        }

        int n = levDistances.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - j); j++) {

                int lev1 = levDistances[j - 1];
                int lev2 = levDistances[j];
                int temp;
                String tempWord;

                if (lev1 > lev2) {
                    temp = lev1;
                    tempWord = dicWordsArrayCopy[j - 1];
                    levDistances[j - 1] = lev2;
                    dicWordsArrayCopy[j - 1] = dicWordsArrayCopy[j];
                    levDistances[j] = temp;
                    dicWordsArrayCopy[j] = tempWord;
                }

            }
        }

        String[] result = new String[N];
        System.arraycopy(dicWordsArrayCopy, 0, result, 0, N);

        return result;
    }

    /**
     * A method that compares two passed Strings to each other and returns the levenshtein distance (or edit distance) between them as an integer.
     * @param wrongWord
     *     The first String that will be compared. Named "wrongWord" to represent the incorrect spelling of a word as a String.
     * @param actualWord
     *     The second String that will be compared. Named "actualWord" to represent the correct spelling of a word as a String.
     * @return
     *     The levenshtein distance between the two passed String as an integer.
     */
    public int getLevenshteinDistance(String wrongWord, String actualWord) {
        // todo
        int length1 = wrongWord.length();
        int length2 = actualWord.length();

        int[][] grid = new int[length1 + 1][length2 + 1];

        for (int i = 0; i < length1 + 1; i++) {
            grid[i][0] = i;
        }
        for (int i = 0; i < length2 + 1; i++) {
            grid[0][i] = i;
        }

        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                char chr1 = wrongWord.charAt(i - 1);
                char chr2 = actualWord.charAt(j - 1);
                boolean areEqual = chr1 == chr2;
                if (areEqual) {
                    grid[i][j] = grid[i - 1][j - 1];

                } else {
                    int a = grid[i - 1][j - 1];
                    int b = grid[i][j - 1];
                    int c = grid[i - 1][j];
                    int min = min(a, b, c);
                    grid[i][j] = min + 1;

                }

            }
        }
        return grid[length1][length2];
    }

    /**
     * A method that returns the minimum of three integers.
     * @param a
     *     Represents the first integer.
     * @param b
     *     Represents the second integer.
     * @param c
     *     Represents the third integer.
     * @return
     *     The minimum value of the three integers as an integer.
     */
    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
    
}
