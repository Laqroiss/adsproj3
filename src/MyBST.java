

public class MyBST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    public MyBST() {
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            return node;
        }
        int comparison = node.key.compareTo(key);
        if (comparison == 0) {
            node.value = value;
        } else if (comparison > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        return node;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        if (node.key.equals(key)) return node.value;
        if (node.key.compareTo(key) > 0) return get(node.left, key);
        return get(node.right, key);
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    public Node delete(Node node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) > 0) node.left = delete(node.left, key);
        else if (node.key.compareTo(key) < 0) node.right = delete(node.right, key);
        else {
            if (node.left == null && node.right == null) return null;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            K min = findMinKey(node.left);
            node.key = min;
            node.left = delete(node.left, min);
        }
        size--;
        return node;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public int getSize() {
        return size;
    }

    private K findMinKey(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }


    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}