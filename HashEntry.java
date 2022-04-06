public class HashEntry{

    String key;
    int value;
    HashEntry next;
    int length = 1;

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
}
