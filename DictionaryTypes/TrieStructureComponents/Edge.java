package DictionaryTypes.TrieStructureComponents;

/**
 * A class that represents an edge in a trie (tree-like data structure) that is built with states and edges.
 */
public class Edge {

    /**
     * The character that the given edge instance represents.
     */
    private char edgeChar;

    /**
     * The state of the given edge instance.
     */
    private State edgeState;

    /**
     * Creates an edge instance with the given character and given state of the edge.
     * @param ch
     *     The character of the new edge instance.
     * @param s
     *     The state of the new edge instance as a State.
     */
    public Edge(Character ch, State s) {
        edgeChar = ch;
        edgeState = s;

    }

    /**
     * A method that returns the state of the current edge instance as a State.
     * @return
     *     The state of the current edge instance as a State.
     */
    public State getEdgeState() {
        return edgeState;

    }
}
