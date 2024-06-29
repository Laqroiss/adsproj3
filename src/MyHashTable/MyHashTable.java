package MyHashTable;


public class MyHashTable<K, V> {


    private HashNode<K, V>[] chainArray;
    private static int M = 11; // default number of chains
    private static final double LOAD_FACTOR_LIMIT = 0.7;
    private int size;

    public MyHashTable() {
        this(M);
    }
    @SuppressWarnings("unchecked")
    public MyHashTable(int new_M) {
        chainArray = (HashNode<K, V>[]) new HashNode[new_M];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    chainArray[index] = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public boolean contains(V value) {
        return getKey(value) != null;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    public int[] containersLen() {
        int[] lengths = new int[chainArray.length];
        for (int i = 0; i < chainArray.length; i++) {
            lengths[i] = containerLen(i);
        }
        return lengths;
    }

    int containerLen(int index) {
        int cnt = 0;
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            cnt++;
            node = node.next;
        }
        return cnt;
    }

    private int hash(K key) {
        checkKey(key);
        return Math.abs(key.hashCode() % chainArray.length);
    }

    private void checkKey(K key) {
        if (key == null) throw new IllegalArgumentException();
    }

    private void checkLoad() {
        if (loadFactor() > LOAD_FACTOR_LIMIT) increaseSize();
    }

    private void increaseSize() {
        HashNode<K, V>[] prev = chainArray;
        chainArray = new HashNode[chainArray.length * 2];
        size = 0;
        for (HashNode<K, V> node : prev) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private double loadFactor() {
        return (double) size / chainArray.length;
    }

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " = " + value + "}";
        }
    }
}


