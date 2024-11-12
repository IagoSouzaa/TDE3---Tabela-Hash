public class HashTableHash2 extends HashTable {
    public HashTableHash2(int size) {
        super(size); 
    }

    @Override
    protected int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i) * (i + 1);
        }
        return Math.abs(hash) % size;
    }
}