# Spellchecker
## The spellchecker works with one of the following data structures (user-specified)
- Array (constant dictionary size)
- Resizing Array (resizes as new words are added or removed)
- Trie (fastest)

Constructor for Spellchecker
```
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
```
