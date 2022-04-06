# P4 Progress Log

## Apr. 3, 2022, 8:00 PM

* Created the Github repository for version control, as well as the Gitbook workspace for updating the progress log along with the project.
* Created the skeletons for Tokenizer, HashEntry, HashTable, and WordStat classes.&#x20;
* Started implementing the word normalization with a BufferedReader.

## Apr. 4, 2022, 9:20 PM

* Created the skeleton of the testing class for Tokenizer.
* Wrote normalizeAndAdd() method.&#x20;
  * Had some trouble with my code not adding the last word of the input string. Fixed it by adding an additional add to the ArrayList after the loop.
  * Had some other issues with accurate word count. Found out that the character used for an apostrophe in the text I was using to test was different than the regular ASCII 39. Found and replaced all and had a much more accurate word count.
* Wrote the code for the constructor that handles adding words from a given array.
  * &#x20;Used a general method that normalizes a given string and adds each word into the ArrayList.
* Wrote the constructor that handles opening and reading a file and normalizing the text.

Time Spent: \~ 1 hr 30 min (10% complete)

## Apr. 5, 2022, 1:00 PM

* Wrote the methods for the testing class TokenizerTester.
  * Made sure to test sentences ending in whitespace, punctuation marks, or letters and numbers.&#x20;
* Wrote the HashEntry class.
* Started writing the HashTable class.&#x20;
  * wrote the two constructors using an ArrayList.
  * Decided to use an array of HashEntry for my HashTable.&#x20;
* Wrote the put() method, and a helper method to increase the size of the HashTable.&#x20;
* I'm a little confused about what the values in the pairs will be for the WordStat class, is it going to be the calculated index?&#x20;
  * ignoring this for now
* Wrote the put() method that is given hashCode



## Apr. 5, 2022, 5:00 PM

* Wrote the update() method
  * Figured out that I was resizing my hash table incorrectly. I was originally just increasing the size of the array and copying over the items. I fixed it to rehash after each resize.&#x20;
* Started writing the get() method.&#x20;
  * &#x20;These methods were pretty straight forward, just hashed the key and iterated through the bucket until the key matched with an entry, then returned the value.
* Changed my put method to ignore duplicates
  * Not sure if this is the correct way but it seems like the correct way to handle this.&#x20;
    * Changed it to increment the value by one if the item is already in the table.&#x20;

Time Spent: \~ 2 hr (50% done)

## Apr. 5, 2022, 7:00 PM&#x20;
