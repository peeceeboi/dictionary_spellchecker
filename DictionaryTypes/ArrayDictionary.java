package DictionaryTypes;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import DictionaryTypes.abstractClasses.ArrayTypeDictionary;
import java.io.FileNotFoundException;

/**
 * A class that represents a dictionary or set of words stored in an array.
 */
public class ArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {


    /**
     * Creates the dictionary instance.
     */
    public ArrayDictionary() { }

    /**
     * The array of type String that stores all the dictionary words.
     */
    public String[] words = new String[30000];

    /**
     * Initialises the array of all words in the dictionary from a text-file on the system.
     * @param filepath
     *     A String that is the path of the text-file on the system.
     */
    public void CreateDictionary(String filepath) {


        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            int count = 0;
            while (scanner.hasNextLine()) {
                words[count] = scanner.nextLine().trim();
                count++;
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
        boolean isWord = false;
        int count = 0;
        while (count < 30000) {
            if (words[count] == null) {
                break;
            }
            if (words[count].trim().equals(word)) {
                isWord = true;
                break;
            }
            count++;
        }

        return isWord;
    }

    /**
     * A method that adds a new given String named word to the current dictionary instance.
     * @param word
     *     The String that will be added to the current dictionary instance.
     */
    public void addNewWord(String word) {
        //todo
        boolean isDuplicate = isDuplicate(word);

        if (!isDuplicate) {
            int count = 0;
            while ((count < 30000)) {
                if (words[count] == null) {
                    words[count] = word;
                    break;
                }
                count++;
            }
        }
    }

    /**
     * A method that removes a specific String from the current dictionary instance.
     * @param word
     *     The String that will be removed from the current dictionary instance.
     */
    public void removeWord(String word) {
        //todo
        int itemIndex = 0;
        boolean wordFound = false;
        while (itemIndex < 30000) {
            if (words[itemIndex] == null) {
                break;
            }
            if (words[itemIndex].equals(word)) {
                wordFound = true;
                break;
            }
            itemIndex++;
        }
        if (wordFound) {
            for (int i = itemIndex; i < 30000; i++) {

                if (i < 29999) {
                    if (words[i] != null) {
                        words[i] = words[i + 1];
                    } else {
                        break;
                    }
                }

            }
        }
    }

    /**
     * A method that returns an ArrayList of type String of all the words in the current dictionary instance.
     * @return
     *     An ArrayList of type String of all the words in the current dictionary instance.
     */
    public ArrayList<String> getDictionaryWords() {
        //todo

        ArrayList<String> arrayList = new ArrayList<String>();
        int count = 0;
        while (count < 30000) {
            if (words[count] == null) {
                break;
            }
            arrayList.add(words[count]);
            count++;
        }
        return arrayList;
    }

    /**
     * A method that returns the number of words in the dictionary.
     * @return
     *     An integer describing the number of words in the dictionary.
     */
    public int getNumberOfElements() {
       //todo

       int count = 0;
        for (int i = 0; i < 30000; i++) {
            if (words[count] != null) {
                count++;
            } else if (words[count] == null) {
                break;
            }
        }
       return count;
    }

    // New methods / non-skeleton:

    /**
     * A method that determines whether a given String already exists in the current dictionary instance.
     * @param word
     *     The String that is checked to determine whether is already exists in the current dictionary instance.
     * @return
     *     A boolean value describing whether the String already exists in the current dictionary instance.
     */
    private boolean isDuplicate(String word) {
        int count = 0;
        boolean isDuplicate = false;
        while ((count < 30000)) {
            if (words[count] == null) {
                break;
            }
            if (words[count].equals(word)) {
                isDuplicate = true;
                break;
            }
            count++;
        }
        return isDuplicate;
    }

}
