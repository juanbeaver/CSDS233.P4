import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TokenizerTester {


    @Test
    /**
     *  Tests the normalizeAndAdd method
     */
    public void testTokenizer(){
        int wordCount = 0;

        // Test adding words from text file,
        // and making sure that the correct amount of words where added
        Tokenizer test = new Tokenizer("testText.txt");
        for(String x:test.wordList()){
            wordCount++;
        }

        assertEquals(32, wordCount);

        // Test adding text from a given String array
        String[] words = {"This", "is", "a", "String array with", "multiple strings of varying lengths",
                          "with 15 words total"};

        Tokenizer test2 = new Tokenizer(words);

        wordCount = 0;

        for(String x:test2.wordList()){
            wordCount++;
        }

        assertEquals(15, wordCount);

    }
}
