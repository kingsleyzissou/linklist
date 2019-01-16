package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Customer LinkedListIterator class
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

class LinkedListIterator<E> implements Iterator<E> {

    private Node<E> head;
    private Node<E> current;

    LinkedListIterator(Node<E> head) {
        this.setHead(head);
        this.setCurrent(null);
    }

    /**
     * Check if the object is the last item in the linked list.
     * If there is an item that follows it, the method
     * returns true, otherwise it returns false
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if  (this.current == null && this.head != null) return true;
        return (this.current != null && this.current.getNext() != null);
    }

    /**
     * Returns the element or data contained in the next available node
     *
     * @return the element contained in the next Node
     */
    @Override
    public E next() {
        if(this.current == null && this.head != null) {
            this.setCurrent(this.head);
            return this.current.getData();
        }
        if(this.current != null) {
            this.setCurrent(this.current.getNext());
            return this.current.getData();
        }
        throw new NoSuchElementException();
    }

    /**
     * Set the head field in the Iterator. The head is a reference to the
     * head in the collections object
     *
     * @param head the reference to the head of the collections class
     */
    private void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * Set the current node int Iterator. This is used to keep track
     * of the current iteration of the Iterator.
     *
     * @param current the current node in the Iterator iteration
     */
    private void setCurrent(Node<E> current) {
        this.current = current;
    }
}
