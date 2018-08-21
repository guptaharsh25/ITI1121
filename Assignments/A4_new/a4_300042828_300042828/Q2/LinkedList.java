import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private static class Node<T> {

        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<E>(null, null, null); // dummy node!
        head.prev = head.next = head;
        size = 0;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head;
        private int pos = 0;

        public boolean hasNext() {
            return (current.next != head);
        }

        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.next;
            pos++;
            return current.value;
        }

        /**
         * @return
         *      the index of the element that would be returned by a subsequent call to next()
         */
        public int nextIndex() {
            return pos;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * @param nextIndex
     *      the index of the element that would be returned by a subsequent call to next()
     *
     * @return
     *       iterator over the elements in this list, starting at the specified position in the list. The specified
     *       index indicates the first element that would be returned by an initial call to next(). The method throws
     *       IndexOutOfBoundsException, if the nextIndex is out of range.
     */
    public Iterator<E> iterator(int nextIndex) {
        if(nextIndex >= size){
            throw new IndexOutOfBoundsException();
        }
        Iterator<E> i = this.iterator();
        int counter = 0;
        while(counter <= nextIndex){
            i.next();
            counter++;
        }

        return i;
    }

    /**
     * @param other
     *       another instance of an iterator that is to be deep copied
     *
     * @return
     *       iterator over the elements in this list, starting at the same position as other. Calling the method next of
     *       this iterator or that of other should return the same element.
     */
    public Iterator<E> iterator(Iterator<E> other) {
        return iterator(other.nextIndex());
    }

    public int size() {
        return size;
    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> p = head.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.value;
    }

    public void addFirst(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> second = head.next;

        head.next = new Node<E>(elem, head, second);
        second.prev = head.next;

        size++;
    }

}
