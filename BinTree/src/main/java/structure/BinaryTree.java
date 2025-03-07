package structure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> root, T data) {
        if (root == null) {
            return new Node<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRecursive(root.left, data);
        } else {
            root.right = insertRecursive(root.right, data);
        }
        return root;
    }

    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> root, T data) {
        if (root == null) {
            return false;
        }

        if (data.compareTo(root.data) == 0) {
            return true;
        }

        if (data.compareTo(root.data) < 0) {
            return searchRecursive(root.left, data);
        } else {
            return searchRecursive(root.right, data);
        }
    }

    public void delete(T data) {
        root = deleteRecursive(root, data);
    }

    private Node<T> deleteRecursive(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = deleteRecursive(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = deleteRecursive(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = findMin(root.right).data;
            root.right = deleteRecursive(root.right, root.data);
        }
        return root;
    }

    private Node<T> findMin(Node<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<T> {
        private final Stack<Node<T>> stack = new Stack<>();
        private Node<T> current;

        public InorderIterator() {
            current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<T> node = stack.pop();
            T result = node.data;

            if (node.right != null) {
                current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            return result;
        }
    }
}
