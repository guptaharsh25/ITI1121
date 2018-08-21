/**
 *  @author Marcel Turcotte
 */

import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> {

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;
        private Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    /**
     * @param low the lower bound
     * @param high the upper bound
     *
     * @return number of elements in the BST that are between the bounds (inclusive)
     */
    public int count(E low, E high){
        return count(low, high, root);
    }

    /**
     * @param low the lower bound
     * @param high the upper bound
     * @param current the current node that we are visiting
     *
     * @return number of elements in the BST that are between the bounds (inclusive)
     */
    private int count(E low, E high, Node<E> current){
        if(current != null){
            Node<E> left = current.left;
            Node<E> right = current.right;

            if ((left == null) && (right == null) && ((low.compareTo(current.value) <= 0) && (high.compareTo(current.value) >= 0))) {
                return 1;
            } else {
                if ((low.compareTo(current.value) <= 0) && (high.compareTo(current.value) >= 0)) {
                    return 1 + count(low, high, left) + count(low, high, right);
                } else if (low.compareTo(current.value) > 0) {
                    return count(low, high, right);
                } else if (high.compareTo(current.value) < 0) {
                    return count(low, high, left);
                }
            }
        }
        return 0;
    }

    private Node<E> root = null;

    /**
     * Inserts an object into this BinarySearchTree.
     *
     * @param elem item to be added
     * @return true if the object has been added and false otherwise
     */

    public boolean add(E elem) {

        // pre-condtion:

        if (elem == null) {
            throw new NullPointerException();
        }

        // special case:

        if (root == null) {
            root = new Node<E>(elem);
            return true;
        }

        // general case:

        return add(elem, root);
    }

    // Helper method

    private boolean add(E elem, Node<E> current) {

        boolean result;
        int test = elem.compareTo(current.value);

        if (test == 0) {
            result = false; // already exists, not added
        } else if (test < 0) {
            if (current.left == null) {
                current.left = new Node<E>(elem);
                result = true;
            } else {
                result = add(elem, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = new Node<E>(elem);
                result = true;
            } else {
                result = add(elem, current.right);
            }
        }
        return result;
    }

    public String toString() {
        return toString(root);
    }

    private String toString(Node<E> current) {

        if (current == null) {
            return "()";
        }

        return "(" + toString(current.left) + current.value + toString(current.right) + ")";
    }

}
