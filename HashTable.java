import java.util.Objects;

public class HashTable {

    HashEntry[] table;
    int entries = 0;
    double load;
    final double loadFactor = .75;

    /**
     *  A constructor for the HashTable class, starts a table with default size of 10.
     */
    HashTable(){
        table = new HashEntry[10];
    }

    /**
     *  A constructor for the HashTable class, starts a table with a given size.
     * @param size the size of the table
     */
    HashTable(int size){
        table = new HashEntry[size];
    }

    /**
     * Method to increase the size of the table.
     */
    public void resize(){
        HashTable newTable = new HashTable(table.length * 2);
        for (HashEntry x : table) {
            if (x != null) {
                newTable.put(x.getKey(), x.getValue());
            }
        }
        table = newTable.table;
    }

    /**
     *  A method to store the given key-value pair into the table.
     *  Resizes the table if necessary
     * @param key String key for the entry
     * @param value int value for the entry
     */
    void put(String key, int value){
        int hashCode = Math.abs(Objects.hashCode(key));
        int indexOfEntry = hashCode % table.length;
        boolean existsInTable = false;
        load = (double) entries / (double) table.length;

        HashEntry entry = new HashEntry(key, value);
        //If load is below loadFactor, continue
        if(load < loadFactor){
            if(table[indexOfEntry] == null){ // If index is empty, add the entry
                table[indexOfEntry] = entry;
                entries++;
            }
            else{
                HashEntry pointer = table[indexOfEntry];
                while(pointer.getNext() != null){
                    if(pointer.getKey().equals(key)){
                        existsInTable = true;
                        pointer.setValue(pointer.getValue()+1);
                        break;
                    }
                    else{
                        pointer = pointer.getNext();
                    }
                }
                if(pointer.getKey().equals(key) && !existsInTable){
                    pointer.setValue(pointer.getValue()+1);
                }
                else if(!existsInTable){
                    pointer = table[indexOfEntry];
                    while(pointer.getNext() != null){
                        pointer = pointer.getNext();
                    }
                    pointer.setNext(entry);
                    entries++;
                }
            }
        }
        else{ //Increase the size of the table and add the key-value pair.
            resize();
            put(key, value);
        }
    }

    /**
     * A method to store the given key-value air into the table using the given hashCode.
     * Resizes the table if necessary.
     * @param key String key for the entry
     * @param value int value for the entry
     * @param hashCode hashCode to be used to calculate index of entry.
     */
    void put(String key, int value, int hashCode){
        int indexOfEntry = Math.abs(hashCode) % table.length;
        boolean existsInTable = false;
        load = (double) entries / (double) table.length;

        HashEntry entry = new HashEntry(key, value);
        //If load is below loadFactor, continue
        if(load < loadFactor){
            if(table[indexOfEntry] == null){ // If index is empty, add the entry
                table[indexOfEntry] = entry;
                entries++;
            }
            else{
                HashEntry pointer = table[indexOfEntry];
                while(pointer.getNext() != null){
                    if(pointer.getKey().equals(key)){
                        existsInTable = true;
                        pointer.setValue(pointer.getValue()+1);
                        break;
                    }
                    else{
                        pointer = pointer.getNext();
                    }
                }
                if(pointer.getKey().equals(key) && !existsInTable){
                    pointer.setValue(pointer.getValue()+1);
                }
                else if(!existsInTable){
                    pointer = table[indexOfEntry];
                    while(pointer.getNext() != null){
                        pointer = pointer.getNext();
                    }
                    pointer.setNext(entry);
                    entries++;
                }
            }
        }
        else{ //Increase the size of the table and add the key-value pair.
            resize();
            put(key, value);
        }
    }

    /**
     * Update the value associated with the key in the table. If the key does not exist
     * It is added to the table.
     * @param key key to be searched.
     * @param value value to change the found key's value to.
     */
    public void update(String key, int value){
        int hashCode = Math.abs(Objects.hashCode(key));
        int indexOfEntry = hashCode % table.length;
        boolean foundOrAdded = false;
        HashEntry bucket = table[indexOfEntry];

        if(bucket != null) {
            if (bucket.getLength() > 1) {
                while (bucket.getNext() != null) {
                    if (bucket.getKey().equals(key)) {
                        foundOrAdded = true;
                        break;
                    }
                    bucket = bucket.getNext();
                }
                if (foundOrAdded) {
                    bucket.setValue(value);
                }
                else{
                    put(key, value);
                }
            }
            else if(bucket.getKey().equals(key)){
                bucket.setValue(value);
                foundOrAdded = true;
            }
            else{
                put(key, value);
            }
        }
        if(!foundOrAdded) {
            put(key, value);
        }
    }

    /**
     * Returns current value associated with the key if it exists in the table, else returns -1
     * @param key key to search for
     * @return value of key, or -1 if key not found
     */
    int get(String key){
        int hashCode = Math.abs(Objects.hashCode(key));
        int IndexOfBucket = hashCode % table.length;
        HashEntry pointer = table[IndexOfBucket];

        while(pointer.getNext() != null){
            if(pointer.getKey().equals(key)){
                return pointer.getValue();
            }
            else{
                pointer = pointer.getNext();
            }
        }
        if(pointer.getKey().equals(key)){
            return pointer.getValue();
        }
        else{
            return -1;
        }
    }

    /**
     * Returns current value associated with the key if it exists in the table, else returns -1
     * Uses given hashCode instead of generating a new one.
     * @param key key to search for
     * @param hashCode hashCode to use for indexing
     * @return  value of found key, or -1 if not found
     */
    int get(String key, int hashCode){
        int IndexOfBucket = Math.abs(hashCode) % table.length;
        HashEntry pointer = table[IndexOfBucket];

        while(pointer.getNext() != null){
            if(pointer.getKey().equals(key)){
                return pointer.getValue();
            }
            else{
                pointer = pointer.getNext();
            }
        }
        if(pointer.getKey().equals(key)){
            return pointer.getValue();
        }
        else{
            return -1;
        }
    }
}
