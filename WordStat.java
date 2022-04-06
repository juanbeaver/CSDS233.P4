import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class WordStat {

    Tokenizer wordsList;
    ArrayList<String> words;
    ArrayList<HashEntry> wordsRanked;
    ArrayList<HashEntry> wordPairsRanked;
    ArrayList<HashEntry> precedingCollocsRanked;
    ArrayList<HashEntry> followingCollocsRanked;

    HashTable wordTable;
    HashTable wordPairTable;
    HashTable precedingCollocTable;
    HashTable followingCollocTable;

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

        System.out.println("Most common collocs for 'on +1:' ");
        String[] mostCommonCollocsFollPairs = words.mostCommonCollocs(10,"on", 1);
        for(String x: mostCommonCollocsFollPairs){
            System.out.println(x);
        }

        System.out.println("Most common collocs for 'on -1:' ");
        String[] mostCommonCollocsPrecPairs = words.mostCommonCollocs(10,"on", -1);
        for(String x: mostCommonCollocsPrecPairs){
            System.out.println(x);
        }
    }

    public WordStat(String fileName) {
        wordsList = new Tokenizer(fileName);
        words = wordsList.wordList();

        wordsRanked = new ArrayList<>();
        wordPairsRanked = new ArrayList<>();
        precedingCollocsRanked = new ArrayList<>();
        followingCollocsRanked = new ArrayList<>();

        wordTable = new HashTable();

        for (String x : words) {
            wordTable.put(x, 1);
        }

        for (int i = 0; i < wordTable.entries - 1; i++) {
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

        precedingCollocTable = new HashTable();

        for(int i = 1; i < words.size() - 1; i++){
            String currColloc = words.get(i - 1) + " " + words.get(i);
            precedingCollocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < precedingCollocTable.table.length - 1; i++) {
            if(precedingCollocTable.table[i] != null){
                HashEntry pointer = precedingCollocTable.table[i];
                while(pointer != null){
                    precedingCollocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        precedingCollocsRanked.sort(Collections.reverseOrder());

        followingCollocTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currColloc = words.get(i) + " " + words.get(i + 1);
            followingCollocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < followingCollocTable.table.length - 1; i++) {
            if(followingCollocTable.table[i] != null){
                HashEntry pointer = followingCollocTable.table[i];
                while(pointer != null){
                    followingCollocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        followingCollocsRanked.sort(Collections.reverseOrder());
    }

    public WordStat(String[] wordArray){
        wordsList = new Tokenizer(wordArray);
        words = wordsList.wordList();
        wordsRanked = new ArrayList<>();
        wordPairsRanked = new ArrayList<>();
        precedingCollocsRanked = new ArrayList<>();
        followingCollocsRanked = new ArrayList<>();

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

        precedingCollocTable = new HashTable();

        for(int i = 1; i < words.size() - 1; i++){
            String currColloc = words.get(i - 1) + " " + words.get(i);
            precedingCollocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < precedingCollocTable.table.length - 1; i++) {
            if(precedingCollocTable.table[i] != null){
                HashEntry pointer = precedingCollocTable.table[i];
                while(pointer != null){
                    precedingCollocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        precedingCollocsRanked.sort(Collections.reverseOrder());

        followingCollocTable = new HashTable();

        for(int i = 0; i < words.size() - 1; i++){
            String currColloc = words.get(i) + " " + words.get(i + 1);
            followingCollocTable.put(currColloc, 1, Math.abs(Objects.hashCode(words.get(i))));
        }

        for (int i = 0; i < followingCollocTable.table.length - 1; i++) {
            if(followingCollocTable.table[i] != null){
                HashEntry pointer = followingCollocTable.table[i];
                while(pointer != null){
                    followingCollocsRanked.add(pointer);
                    pointer = pointer.getNext();
                }
            }
        }

        followingCollocsRanked.sort(Collections.reverseOrder());
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
        String[] collocsToReturn = new String[k];

        if( i == 1){
            int indexBase = Math.abs(Objects.hashCode(baseWord)) % followingCollocTable.table.length;
            HashEntry baseWordBucket = followingCollocTable.table[indexBase];
            for(int j = 0; j < k; j++){
                collocsToReturn[j] = baseWordBucket.getKey();
                baseWordBucket = baseWordBucket.getNext();
            }
            return collocsToReturn;
        }
        else if(i == -1){
            int indexBase = Math.abs(Objects.hashCode(baseWord)) % precedingCollocTable.table.length;
            HashEntry baseWordBucket = precedingCollocTable.table[indexBase];
            for(int j = 0; j < k; j++){
                collocsToReturn[j] = baseWordBucket.getKey();
                baseWordBucket = baseWordBucket.getNext();
            }
            return collocsToReturn;
        }
        else{
            return null;
        }
    }
}
