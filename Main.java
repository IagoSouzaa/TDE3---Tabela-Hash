import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String filename = "female_names.txt";
        List<String> names = readNamesFromFile(filename);
        int tableSize = 10007; 

        HashTable hashTable1 = new HashTableHash1(tableSize);
        HashTable hashTable2 = new HashTableHash2(tableSize);

        long startTime, endTime;

        startTime = System.nanoTime();
        for (String name : names) {
            hashTable1.insert(name);
        }
        endTime = System.nanoTime();
        long insertionTime1 = endTime - startTime;

        startTime = System.nanoTime();
        for (String name : names) {
            hashTable2.insert(name);
        }
        endTime = System.nanoTime();
        long insertionTime2 = endTime - startTime;

        startTime = System.nanoTime();
        for (String name : names) {
            hashTable1.search(name);
        }
        endTime = System.nanoTime();
        long searchTime1 = endTime - startTime;

        startTime = System.nanoTime();
        for (String name : names) {
            hashTable2.search(name);
        }
        endTime = System.nanoTime();
        long searchTime2 = endTime - startTime;

        System.out.println("=== Relatório de Eficiência ===");

        System.out.println("Tabela Hash 1:");
        System.out.println("Número de colisões: " + hashTable1.getCollisionCount());
        System.out.println("Tempo de inserção: " + insertionTime1 + " ns");
        System.out.println("Tempo de busca: " + searchTime1 + " ns");
        printLimitedDistribution(hashTable1, 10);

        System.out.println("\nTabela Hash 2:");
        System.out.println("Número de colisões: " + hashTable2.getCollisionCount());
        System.out.println("Tempo de inserção: " + insertionTime2 + " ns");
        System.out.println("Tempo de busca: " + searchTime2 + " ns");
        printLimitedDistribution(hashTable2, 10);
    }

    private static List<String> readNamesFromFile(String filename) {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.trim());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading from file: " + filename, e);
        }
        return names;
    }

    private static void printLimitedDistribution(HashTable table, int limit) {
        int[] distribution = table.getDistribution();
        System.out.println("Distribuição de chaves nas primeiras " + limit + " posições da tabela:");

        for (int i = 0; i < limit; i++) {
            System.out.println("Posição " + i + ": " + distribution[i] + " chaves");
        }
    }
}