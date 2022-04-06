import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashTableTester {

    @Test
    public void testPut(){
        HashTable testTable = new HashTable();
        testTable.put("Juan", 5);
        testTable.put("Juan", 5); //Should increment original entry by 5
        assertEquals(6, testTable.get("Juan"));

        testTable.put("Beaver", 5);
        testTable.put("Cristian", 5);
        testTable.put("Milton", 5);

        // Checks that collisions are handled correctly, should chain to key "Beaver"
        testTable.put("Carlos", 13, Math.abs(Objects.hashCode("Beaver")));
        int indexOfBeaver = Math.abs(Objects.hashCode("Beaver")) % testTable.table.length;
        assertEquals(2,testTable.table[indexOfBeaver].getLength());
        // Ensure entries are in the right place
        assertEquals("Beaver", testTable.table[indexOfBeaver].getKey());
        assertEquals("Carlos", testTable.table[indexOfBeaver].getNext().getKey());

        // Checks to see if the correct amount of entries where entered
        // Checks by both using the entry counter in the class, and iterating through the table.
        int count = 0;
        for(HashEntry x: testTable.table){
            if(x != null) {
                if(x.getNext() == null) {
                    count++;
                }
                else{
                    HashEntry pointer = x;
                    while(pointer != null){
                        count++;
                        pointer = pointer.getNext();
                    }
                }
            }
        }
        assertEquals(5, count);
        assertEquals(testTable.entries, count);

    }

    @Test
    public void testUpdate(){
        HashTable testTable = new HashTable();
        testTable.put("first", 1);
        testTable.put("third", 1);

        // Tests updating the value of an entry that exists
        testTable.update("first", 2);
        assertEquals(2, testTable.get("first"));

        // Tests updating an entry that does not exist
        testTable.update("second", 12);
        assertEquals(12, testTable.get("second"));
    }

    @Test
    public void testGet(){

        // Tests getting an entry from the HashTable
        HashTable testTable = new HashTable();
        testTable.put("first", 13);
        testTable.put("second", 21);

        assertEquals(13, testTable.get("first"));
    }

}
