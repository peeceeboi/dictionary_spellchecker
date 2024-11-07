package DictionaryTypes;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

/**
 * A class that represents a dictionary or set of words stored in an array that resizes as words are added or removed.
 */
public class ResizingArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {

    /**
     * The array of type String that stores all the dictionary words.
     */
    public String[] words = new String[5];

    /**
     * The current size of the array that stores the dictionary of words.
     */
    private int size = 5;

    /**
     * Creates the dictionary instance.
     */
    public void CreateDictionary(String filepath) {

        int wordCount = 0;

        try {
            Scanner scanner = new Scanner(new FileReader(filepath));

            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                wordCount++;

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        size = wordCount + 5;
        words = new String[size];

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
        while (count < size) {
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

        boolean isDuplicate = isDuplicate(word);

        if (!isDuplicate) {
            int count = 0;
            while ((count <= size)) {
                if (count == size) {
                    size = size * 2;
                    words = Arrays.copyOf(words, size);

                }
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

        int itemIndex = 0;
        boolean wordFound = false;
        while (itemIndex < size) {
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
            for (int i = itemIndex; i < size; i++) {

                if (i < size - 1) {
                    if (words[i] != null) {
                        words[i] = words[i + 1];
                    } else {
                        break;
                    }
                }

            }
        }

        int numWords = getNumberOfElements();
        double frac = ((double) numWords / size);
        if (size > 5 && (frac <= 0.25)) {

            size = (size / 2);
            words = Arrays.copyOf(words, size);

        }

    }

    /**
     * A method that returns an ArrayList of type String of all the words in the current dictionary instance.
     * @return
     *     An ArrayList of type String of all the words in the current dictionary instance.
     */
    public ArrayList<String> getDictionaryWords() {
        ArrayList<String> arrayList = new ArrayList<String>();
        int count = 0;
        while (count < size) {
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
        for (int i = 0; i < size; i++) {
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
        while ((count < size)) {
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
