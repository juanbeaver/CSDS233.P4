public class HashEntry implements Comparable<HashEntry> {

    String key;
    int value;
    HashEntry next;
    int length = 1;
    int hashCode = 0;

    HashEntry(String key, int value){
        this.key = key;
        this.value = value;
    }


    public String getKey(){
        return key;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public HashEntry getNext(){
        return next;
    }

    public void setNext(HashEntry next){
        this.next = next;
    }

    public int getLength(){
        HashEntry pointer = this;
        int count = 1;
        while(pointer.getNext() != null){
            count++;
            pointer = pointer.getNext();
        }
        return count;
    }

    public int compareTo(HashEntry o){
        return Integer.compare(this.getValue(), o.getValue());
    }
}
