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
