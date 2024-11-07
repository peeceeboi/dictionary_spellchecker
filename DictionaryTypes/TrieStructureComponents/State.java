package DictionaryTypes.TrieStructureComponents;
import java.util.ArrayList;

/**
 * A class that represents a state of an edge in a trie (tree-like structure) built with edges and states.
 */
public class State {

    /**
     * The ArrayList of all outgoing edges of the current state instance.
     */
    private ArrayList<Edge> outgoingEdges = new ArrayList<Edge>();

    /**
     * A boolean value that describes whether the word up until this specific state in the trie is a word in the dictionary set.
     */
    public boolean isWord;

    /**
     * A String value that describes the word up until this specific state in the trie.
     */
    public String wordUpUntil;

    /**
     * Creates a new state instance with a boolean value - accept - that describes whether the word up until the state is a word in the dictionary set and a String - wordUpUntil - that describes the word up until this specific state in the trie.
     * @param accept
     *     A boolean value that describes whether the word up until this specific state in the trie is a word in the dictionary set.
     * @param wordUpUntil
     *     A String value that describes the word up until this specific state in the trie.
     */
    public State(boolean accept, String wordUpUntil) {
        this.isWord = accept;
        this.wordUpUntil = wordUpUntil;

    }

    /**
     * A method that returns an ArrayList of all outgoing edges connected to this specific state in the trie.
     * @return
     *     The ArrayList of all outgoing edges of the current state instance.
     */
    public ArrayList<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

}
