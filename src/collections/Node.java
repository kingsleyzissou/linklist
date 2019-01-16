package collections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Custom Node class
 *
 * Nodes are added to LinkedList and contain a reference to the next node.
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Node<E> {

    private Node<E> next;
    private E data;

    public Node () {
        this.next = null;
        this.data = null;
    }

    public Node (E data) {
        this.next = null;
        this.data = data;
    }

    /**
     * Returns the reference to the next node
     * in the linked list
     *
     * @return Node<E> next
     */
    Node<E> getNext() {
        return next;
    }

    /**
     * Set the reference to the next node in the list
     *
     * @param next reference to the next node in the linked list
     */
    void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Returns the data contained in a Node object
     *
     * @return data
     */
    public E getData() {
        return data;
    }

    /**
     * Sets the contents of the data contained in a
     * Node object
     *
     * @param data the data that is to be contained in the Node object
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns a string of the state of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
