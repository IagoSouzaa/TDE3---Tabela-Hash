public abstract class HashTable {
    protected int size;
    protected String[] table;
    protected int collisionCount;

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
        this.collisionCount = 0;
    }

 
    protected abstract int hash(String key);

  
    public void insert(String key) {
        int index = hash(key);
        if (table[index] != null) {
            collisionCount++;
        }
        table[index] = key;
    }

    public boolean search(String key) {
        int index = hash(key);
        return table[index] != null && table[index].equals(key);
    }

   
    public int getCollisionCount() {
        return collisionCount;
    }

  
    public int[] getDistribution() {
        int[] distribution = new int[size];
        for (String s : table) {
            if (s != null) {
                int index = hash(s);
                distribution[index]++;
            }
        }
        return distribution;
    }
}