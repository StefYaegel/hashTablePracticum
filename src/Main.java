
import java.util.*;
import java.util.Hashtable;
class Main {
    public static void main(String[] args) {

        // set size of hashtable
        int tableSize = 16;

        // declare hashtable ot hold integers
        Hashtable<Integer, Integer> hashTable = new Hashtable<>(tableSize);

        // Array of 16 integer keys
        int [] keysArray = {1, 27, 10, 33, 15, 37, 22, 8, 9, 30, 21, 18, 2, 11, 29, 25};

        // for each key, go through the hashtable to find a slot while handling any collisions
        for (int i = 0; i < keysArray.length; i++){
            int key = keysArray[i];
            System.out.print("\nKey: " + key + ", ");
            int H1 = firstHashFunction(key, tableSize);
            int H2 = secondHashFunction(key, tableSize);
            determineSlot(H1, H2, tableSize, hashTable, key);
        }
    }

    // Set the first hash function
    public static int firstHashFunction (int key, int tableSize){

        int H1 = key % tableSize;
        System.out.print("H1: " + H1 + ", ");
        return H1;

    }

    // Set the second hash function
    public static int secondHashFunction (int key, int tableSize){

        int H2 = 2 * (key % 4) + 1;
        System.out.print("H2: " + H2);
        return H2;

    }

    // Determine table slot for key and handle collisions
    public static void determineSlot(int H1, int H2, int tableSize, Hashtable<Integer, Integer> hashTable, int key){

        // initially i is set to 0 and is incremented when collision occurs
        int i = 0;

        // while there are still more empty slots, determine slot for this key
        while (i < tableSize){

            int slot = (H1 + (H2 * i)) % tableSize;
            System.out.print("\nSlot determined: "+ slot + "; ");
            System.out.print("Probe i = " + i);

            // check if collision (when slot is not empty) - must rehash if so
            if (hashTable.get(slot) != null){
                System.out.print("\nCollision at slot #" + slot);
                System.out.print("\nProbing table for an available slot again...");
                i++;
            } else if (hashTable.get(slot) == null) {
                System.out.println("\nFound (key, slot): (" + key + "," + slot +")");
                hashTable.put(slot, key);
                break;
            }
        }

        // if amount of keys is greater than hashtable capacity or not
        if (i == tableSize){
            System.out.print("\nTable is full, could not insert key " + (key) +"\n");
            System.out.println("Hashtable hasn't changed:");
            for (int h = 0; h < tableSize; h++) {
                System.out.println(String.format("Slot %2d: %2d", (h), hashTable.get(h)));
            }
        } else {
            // print each time a key is successfully put into hashtable
            System.out.println("Current Hashtable:");
            for (int h = 0; h < tableSize; h++) {
                System.out.println(String.format("Slot %2d: %2d", (h), hashTable.get(h)));
            }
        }
    }
}