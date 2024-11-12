public class HashTableHash1 extends HashTable {
    public HashTableHash1(int size) {
        super(size); 
    }

    @Override
    protected int hash(String key) {
        int hash = 7;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * 31 + key.charAt(i);
        }
        return Math.abs(hash) % size;
    }
}