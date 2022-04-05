import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Tokenizer class that provides the functionality to read lines of text from a file,
 * extract and normalize words, and store them in an ArrayList.
 */
public class Tokenizer {

    private BufferedReader reader;
    private ArrayList<String> wordList = new ArrayList<>();

    public static void main(String[] args){
        int wordCount = 0;
        Tokenizer test = new Tokenizer("TextFile.txt");

        for(String x: test.wordList()){
            wordCount++;
            System.out.println(x);
        }
        System.out.println(wordCount);
    }

    /**
     * Helper method that normalizes a given string into
     * separate words and adds those words into the ArrayList
     * @param sentence
     */
    public void normalizeAndAdd(String sentence){
        StringBuilder word = new StringBuilder();

        for(int i = 0; i < sentence.length(); i++){
            //if letter or digit append to temp String
            if(Character.isLetterOrDigit(sentence.charAt(i))){
                word.append(sentence.charAt(i));
            }
            //If character is hyphen or apostrophe do nothing
            else if(sentence.charAt(i) == 39 || sentence.charAt(i) == 45) {
                ;
            }
            else{
                if(word.toString().length() > 0){
                    wordList.add(word.toString().toLowerCase());
                    word.delete(0, word.length());
                }
            }
        }
        if(word.toString().length() > 0){
            wordList.add(word.toString().toLowerCase());
        }
    }

    /**
     * A constructor that takes in the file location of the file to be read.
     * @param file
     * @throws IOException
     */
    Tokenizer(String file){
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                normalizeAndAdd(line);
            }
        }
        catch (IOException e) {
            ;
        }
    }

    /**
     * A constructor that takes a string of words to process and store.
     * @param words
     */
    Tokenizer(String[] words){
        for(String x : words){
            normalizeAndAdd(x);
        }
    }

    /**
     * A method that returns a list of words in an ArrayList<String>.
     * @return
     */
    ArrayList<String> wordList(){
        return wordList;
    }
}
