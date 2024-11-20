import java.util.ArrayList;
import java.util.List;

class HashMapEntry {
    int key;
    List<Event> events;
    boolean isDeleted;

    HashMapEntry(int key) {
        this.key = key;
        this.events = new ArrayList<>();
        this.isDeleted = false;
    }
}

class CustomHashMap {
    private static final int SIZE = 100;
    private HashMapEntry[] table;

    public CustomHashMap() {
        table = new HashMapEntry[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    private int probe(int key, int i) {
        return (hash(key) + i * i) % SIZE;
    }

    public void put(int key, Event event) {
        int i = 0;
        while (true) {
            int index = probe(key, i);
            if (table[index] == null || table[index].isDeleted) {
                table[index] = new HashMapEntry(key);
                table[index].events.add(event);
                return;
            } else if (table[index].key == key) {
                table[index].events.add(event);
                return;
            }
            i++;
        }
    }

    public List<Event> get(int key) {
        int i = 0;
        while (true) {
            int index = probe(key, i);
            if (table[index] == null) {
                return new ArrayList<>();
            } else if (table[index].key == key && !table[index].isDeleted) {
                return table[index].events;
            }
            i++;
        }
    }

    public void delete(int key, Event event) {
        int i = 0;
        while (true) {
            int index = probe(key, i);
            if (table[index] == null) {
                return;
            } else if (table[index].key == key && !table[index].isDeleted) {
                table[index].events.remove(event);
                if (table[index].events.isEmpty()) {
                    table[index].isDeleted = true;
                }
                return;
            }
            i++;
        }
    }
}
