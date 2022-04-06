import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class WordStatTester {
    @Test
    public void testWordCount(){

        // Tests that wordCount returns accurate count of word that exists
        WordStat wordStat = new WordStat("testText.txt");
        assertEquals(4, wordStat.wordCount("this"));

        // Tests word count for word that does not exist in text
        assertEquals(0, wordStat.wordCount("not"));
    }

    @Test
    public void testWordPairCount(){
        // Tests that wordPairCount returns accurate count of word that exists
        WordStat wordPairStat = new WordStat("testText.txt");
        assertEquals(2, wordPairStat.wordPairCount("this", "is"));

        // Tests word count for word pair that does not exist in text
        assertEquals(0, wordPairStat.wordPairCount("not", "here"));
    }

    @Test
    public void testWordRank(){
        WordStat wordStat = new WordStat("testText.txt");

        assertEquals(1, wordStat.wordRank("this"));

        assertEquals(1, wordStat.wordRank("is"));
    }

    @Test
    public void testWordPairRank(){

    }

    @Test
    public void testMostCommonWords(){

    }

    @Test
    public void testLeastCommonWords(){

    }

    @Test
    public void testMostCommonWordPairs(){

    }

    @Test
    public void testMostCommonCollocs(){

    }

}
