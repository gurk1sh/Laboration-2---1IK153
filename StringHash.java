package GenericList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class StringHash<T> implements Iterable<T> {
    private LinkedList<T>[] hashTable;
    private int size = 11;
    private int amountOfElements = 0;

    public StringHash() {
        hashTable = new LinkedList[size];
    }

    private int hash(T s) {
        int h = s.hashCode();
        if (h < 0) {
            h = -h;
        }
        return h % size;
    }

    public boolean contains(T t) {
        int pos = hash(t);

        if (hashTable[pos] != null) {
            return hashTable[pos].contains(t);
        }
        return false;
    }

    private void reHash() {
        LinkedList<T> oldValues = new LinkedList<>();

        for (int i = 0; i < size; i++) { //Copy old values to new linkedlist
            if (hashTable[i] != null) {
                for (T s : hashTable[i]) {
                    oldValues.add(s);
                }
            }
        }

        size *= 2;
        amountOfElements = 0;
        hashTable = new LinkedList[size]; //Create new hash table

        for (Object s : oldValues) //Add all old values to new
        {
            this.add((T) s);
        }

    }

    public void add(T s) {
        int bkt = hash(s);

        if (this.contains(s)) {
            System.out.println("Error, tried to add " + s + " more than once!");
            return;
        }

        if ((double) amountOfElements / (double) size > 0.75) {
            reHash();
        }

        if (hashTable[bkt] == null) {
            hashTable[bkt] = new LinkedList<>();
        }
        hashTable[bkt].add(s);
        amountOfElements++;
    }

    public boolean remove(T s) {
        if (this.contains(s)) {
            return false;
        }

        int pos = hash(s);

        if (hashTable[pos] != null) {
            boolean res = hashTable[pos].remove(s);
            if (hashTable[pos].isEmpty()) {
                hashTable[pos] = null;
            }
            amountOfElements--;
            return res;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void showHashTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            if (hashTable[i] == null) {
                System.out.print("null");
            } else {
                for (T s : hashTable[i]) {
                    System.out.print(s + " -> ");
                }
            }
            System.out.println();
        }
    }

    public Iterator<T> iterator() {

        return new HashIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }


    private class HashIterator implements Iterator<T> {
        private ArrayList<T> list = new ArrayList<>();
        private int current = 0;

        public HashIterator() {
            for (int i = 0; i < size; i++) {

                if (hashTable[i] != null) {
                    for (T s : hashTable[i]) {
                        list.add((T) s);
                    }
                }

            }
        }

        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        public T next() {

            return (T) list.get(current++);
        }

    }

}
