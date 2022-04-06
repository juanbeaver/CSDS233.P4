import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class WordStat {

    Tokenizer wordsList;
    ArrayList<String> words;
    ArrayList<HashEntry> wordsRanked;
    ArrayList<HashEntry> wordPairsRanked;
    ArrayList<HashEntry> collocsRanked;

    HashTable wordTable;
    HashTable wordPairTable;
    HashTable collocTable;

    public static void main(String[] args) {

        WordStat words = new WordStat("TextFile.txt");

        System.out.println("Most common words: ");
        String[] mostCommonWords = words.mostCommonWords(10);
        for(String x: mostCommonWords){
            System.out.println(x);
        }

        System.out.println("Least common words: ");
        String[] leastCommonWords = words.leastCommonWords(10);
        for(String x: leastCommonWords){
            System.out.println(x);
        }

        System.out.println("Most common word pairs: ");
        String[] mostCommonWordPairs = words.mostCommonWordPairs(10);
        for(String x: mostCommonWordPairs){
            System.out.println(x);
        }
    }

    public WordStat(String fileName) {
        wordsList = new Tokenizer(fileName);
        words = wordsList.wordList();
        wordsRanked = new ArrayList<>();
        wordPairsRanked = new ArrayList<>();
        collocsRanked = new ArrayList<>();

        wordTable = new HashTable();

        for (String x : words) {
            wordTable.put(x, 1);
        }

        for (int i = 0; i < wordTable.table.length - 1; i++) {
            if(wordTable.table[i] != null){
                HashEntry pointer = wordTable.table[i];
                while(pointer != null){
                    wordsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        wordsRanked.sort(Collections.reverseOrder());

        wordPairTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currPair = words.get(i) + " " + words.get(i + 1);
            wordPairTable.put(currPair, 1);
        }

        for (int i = 0; i < wordPairTable.table.length - 1; i++) {
            if(wordPairTable.table[i] != null){
                HashEntry pointer = wordPairTable.table[i];
                while(pointer != null){
                    wordPairsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        wordPairsRanked.sort(Collections.reverseOrder());

        collocTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currColloc = words.get(i) + " " + words.get(i + 1);
            collocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < collocTable.table.length - 1; i++) {
            if(collocTable.table[i] != null){
                HashEntry pointer = collocTable.table[i];
                while(pointer != null){
                    collocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        collocsRanked.sort(Collections.reverseOrder());

    }

    public WordStat(String[] wordArray){
        wordsList = new Tokenizer(wordArray);
        words = wordsList.wordList();
        wordsRanked = new ArrayList<>();
        wordPairsRanked = new ArrayList<>();
        collocsRanked = new ArrayList<>();

        HashTable wordTable = new HashTable();

        for (String x : words) {
            wordTable.put(x, 1);
        }

        for (int i = 0; i < wordTable.table.length - 1; i++) {
            if(wordTable.table[i] != null){
                HashEntry pointer = wordTable.table[i];
                while(pointer != null){
                    wordsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        wordsRanked.sort(Collections.reverseOrder());

        HashTable wordPairTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currPair = words.get(i) + " " + words.get(i + 1);
            wordPairTable.put(currPair, 1);
        }

        for (int i = 0; i < wordPairTable.table.length - 1; i++) {
            if(wordPairTable.table[i] != null){
                HashEntry pointer = wordPairTable.table[i];
                while(pointer != null){
                    wordPairsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        wordPairsRanked.sort(Collections.reverseOrder());

        collocTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currColloc = words.get(i) + " " + words.get(i + 1);
            collocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < collocTable.table.length - 1; i++) {
            if(collocTable.table[i] != null){
                HashEntry pointer = collocTable.table[i];
                while(pointer != null){
                    collocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        collocsRanked.sort(Collections.reverseOrder());
    }

    public int wordCount(String word){
        boolean found = false;
        int indexWord = Math.abs(Objects.hashCode(word)) % wordTable.table.length;
        HashEntry pointer = wordTable.table[indexWord];
        while(pointer != null){
            if(pointer.getKey().equals(word)){
                found = true;
                break;
            }
            pointer = pointer.getNext();
        }
        if(found){
            return pointer.getValue();
        }
        return 0;
    }

    public int wordPairCount(String w1, String w2){
        boolean found = false;
        String wordPair = w1 + " " + w2;
        int indexPair = Math.abs(Objects.hashCode(wordPair)) % wordPairTable.table.length;
        HashEntry pointer = wordPairTable.table[indexPair];
        while(pointer != null){
            if(pointer.getKey().equals(wordPair)){
                found = true;
                break;
            }
            pointer = pointer.getNext();
        }
        if(found) {
            return pointer.getValue();
        }
        return 0;
    }

    public int wordRank(String word){
        for(HashEntry x: wordsRanked){
            if(x.getKey().equals(word)){
                return wordsRanked.indexOf(x) + 1;
            }
        }
        return -1;
    }

    public int wordPairRank(String w1, String w2){
        String word = w1 + " " + w2;
        for(HashEntry x: wordPairsRanked){
            if(x.getKey().equals(word)){
                return wordPairsRanked.indexOf(x) + 1;
            }
        }
        return -1;
    }

    public String[] mostCommonWords(int k){
        String[] wordsToReturn = new String[k];
        for(int i = 0; i < k; i++){
            wordsToReturn[i] = wordsRanked.get(i).getKey();
        }
        return wordsToReturn;
    }

    public String[] leastCommonWords(int k){
        String[] wordsToReturn = new String[k];
        for(int i = 0; i < k; i++){
            wordsToReturn[i] = wordsRanked.get(wordsRanked.size()-1-i).getKey();
        }
        return wordsToReturn;
    }

    public String[] mostCommonWordPairs(int k){
        String[] wordsToReturn = new String[k];
        for(int i = 0; i < k; i++){
            wordsToReturn[i] = wordPairsRanked.get(i).getKey();
        }
        return wordsToReturn;
    }

    public String[] mostCommonCollocs(int k, String baseWord, int i){
        return null;
    }
}
