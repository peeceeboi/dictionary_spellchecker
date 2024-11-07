package DictionaryTypes;
import DictionaryTypes.TrieStructureComponents.Edge;
import DictionaryTypes.TrieStructureComponents.State;
import DictionaryTypes.abstractClasses.Dictionary;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that represents a dictionary or set of words stored in a trie (tree-like data structure).
 */
public class TrieDictionary extends Dictionary implements DictionaryInterface {

    State rootState = new State(false, "");
    private int totalWords = 0;

    public void CreateDictionary(String filePath) {
        //todo
        int wordsAdded = 0;

        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                addNewWord(word.trim());

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * A method that determines whether a given String named word is a word or is a part of the current dictionary instance.
     * @param word
     *     A String that is checked to determine whether it exists in the current dictionary instance.
     * @return
     *     A boolean that describes whether the String exists in the current dictionary instance.
     */
    public boolean isWord(String word) {
        // Get current state
        State currentState = rootState;

        // Loop through characters in the word their outgoing edges by numbering edges according to ASCII values (0-25)
        for (int index = 0; index < word.length(); index++) {

            int chrIndex = word.charAt(index) - 'a';

            if (currentState.getOutgoingEdges().get(chrIndex) == null) {
                return false;

            } else {
                currentState = currentState.getOutgoingEdges().get(chrIndex).getEdgeState();

            }
        }

        return currentState.isWord;

    }

    /**
     * A method that adds a new given String named word to the current dictionary instance.
     * @param w
     *     The String that will be added to the current dictionary instance.
     */
    public void addNewWord(String w) {
        //todo

        w = w.toLowerCase();

        // Loop through states:
        State currentState = rootState;
        for (int i = 0; i < w.length(); i++) {
            // Get char:
            char c = w.charAt(i);

            // Get index of char:
            int index = c - 'a';

            // Initialise with null values in order to populate with correct indexes
            // Size of edge arraylist needs to be at least (index + 1)
            if (currentState.getOutgoingEdges().size() < index + 1) {
                for (int j = currentState.getOutgoingEdges().size(); j < index + 1; j++) {
                    currentState.getOutgoingEdges().add(j, null);
                }
            }

            if (currentState.getOutgoingEdges().get(index) == null) {
                State newState = new State(false, w.substring(0, i + 1));
                currentState.getOutgoingEdges().set(index, new Edge(c, newState));
                currentState = currentState.getOutgoingEdges().get(index).getEdgeState();

            } else {
                currentState = currentState.getOutgoingEdges().get(index).getEdgeState();
            }

        }
        currentState.isWord = true;
        totalWords++;
    }

    /**
     * A method that returns an ArrayList of type String of all the words in the current dictionary instance.
     * @return
     *     An ArrayList of type String of all the words in the current dictionary instance.
     */
    public ArrayList<String> getDictionaryWords() {
        //todo

        // Loop through rootState recursively to find all states that include words in set

        // Get root state edge indexes
        ArrayList<Integer> connectedStatesIndex = new ArrayList<>();
        for (int i = 0; i < rootState.getOutgoingEdges().size(); i++) {
            if (rootState.getOutgoingEdges().get(i) != null) {
                connectedStatesIndex.add(i);
            }
        }

        ArrayList<String> words = new ArrayList<String>();

        if (connectedStatesIndex.size() != 0) {
            words = searchWordsRecurse(words, rootState, connectedStatesIndex);

        }

        return words;
    }

    /**
     * A method that returns the number of words in the dictionary.
     * @return
     *     An integer describing the number of words in the dictionary.
     */
    public int getNumberOfElements() {
        //todo
        return totalWords;
    }

    /**
     * A method that finds the longest common prefix between all the words in the current dictionary instance and the given word.
     * @param word
     *     The String that will be searched for in the current dictionary instance to find the longest common prefix in the dictionary.
     * @return
     *     The longest common prefix between the given word and the dictionary as a String.
     */
    public String findCommonPrefix(String word) {
        //todo
        String commonPrefix = "";

        State currentState = rootState;

        for (int index = 0; index < word.length(); index++) {

            int chrIndex = word.charAt(index) - 'a';

            if (currentState.getOutgoingEdges().size() - 1 < chrIndex) {
                return currentState.wordUpUntil;
            }

            if (currentState.getOutgoingEdges().get(chrIndex) == null) {
                return currentState.wordUpUntil;

            } else {
                currentState = currentState.getOutgoingEdges().get(chrIndex).getEdgeState();
                commonPrefix = currentState.wordUpUntil;
            }

        }

        return commonPrefix;

    }

    /**
     * A method that removes a specific String from the current dictionary instance.
     * @param word1
     *     The String that will be removed from the current dictionary instance.
     */
    public void removeWord(String word1) {
        //todo
        State currentState = rootState;
        String word = word1;

        for (int index = 0; index < word.length(); index++) {

            int chrIndex = word.charAt(index) - 'a';

            if (currentState.getOutgoingEdges().get(chrIndex) == null) {
                return;

            } else {
                currentState = currentState.getOutgoingEdges().get(chrIndex).getEdgeState();

            }

            if (currentState.wordUpUntil.equals(word)) {
                currentState.isWord = false;
                return;
            }

        }
        totalWords--;
    }

    /**
     * A method that returns an ArrayList of all words in the current dictionary instance by looping through the trie recursively.
     * @param currentWords
     *     The current ArrayList of words that have been found in the current dictionary instance.
     * @param currentState
     *     The current State where the loop is navigating.
     * @param connectedStatesIndexes
     *     The current ArrayList of type integer storing the indexes of all the connected edges to the current state.
     * @return
     *     An ArrayList of all words in the current dictionary instance.
     */
    private ArrayList<String> searchWordsRecurse(ArrayList<String> currentWords, State currentState, ArrayList<Integer> connectedStatesIndexes) {

        // Loop through indexes recursively by checking the length of their connectedStates for 0

        if (connectedStatesIndexes.size() != 0) {
            for (int i = 0; i < connectedStatesIndexes.size(); i++) {

                ArrayList<Integer> newConnectedStatesIndexes = new ArrayList<>();

                State newState = currentState.getOutgoingEdges().get(connectedStatesIndexes.get(i)).getEdgeState();

                for (int j = 0; j < newState.getOutgoingEdges().size(); j++) {
                    if (newState.getOutgoingEdges().get(j) != null) {
                        newConnectedStatesIndexes.add(j);
                    }

                }


                if (currentState.isWord && !(currentWords.contains(currentState.wordUpUntil))) {
                    currentWords.add(currentState.wordUpUntil);

                }
                currentWords = searchWordsRecurse(currentWords, newState, newConnectedStatesIndexes);
            }


        } else {
            if (currentState.isWord) {
                currentWords.add(currentState.wordUpUntil);

            }
        }
        return currentWords;
    }


}
