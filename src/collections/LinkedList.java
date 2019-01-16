package collections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Iterator;

/**
 * Customer LinkedList class
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class LinkedList<E> implements Iterable<E> {

    private int count;
    private Node<E> head;

    public LinkedList() {
        this.setHead(null);
        this.setCount(0);
    }

    /**
     * Add an element to a link list
     *
     * @param data
     */
    public void add(E data) {
        Node<E> node = new Node<>(data);
        node.setNext(this.getHead());
        this.setHead(node);
        this.incrementCount();
    }

    /**
     * Delete a node from the link list and
     * return the value/object that was stored
     * in the node
     *
     * @param data
     * @return Generic object E
     */
    public E delete(E data) {
        // Get the head node
        Node<E> head = this.getHead();

        // if the head is null, the list is empty
        if (head == null) return null;

        // if the head is the sought after node,
        // remove the head and set the head to the next node
        if(head.getData().equals(data)) {
            this.setHead(head.getNext());
            this.decrementCount();
            return head.getData();
        }

        // Set node to head (a new variable was created here for clarity purposes
//        Node<E> node = this.getHead();

        // Loop through the nodes in the list
        for(Node<E> node = this.getHead(); node.getNext() != null; node = node.getNext()) {
            // keep a reference to the next node in the list and the current node
            // get the data of the next node
            Node<E> next = node.getNext();
            E nextData = next.getData();
            // if the data in the next node is the sought after data,
            // set the reference to the current node to the node after the 'next' node
            if (nextData.equals(data)) {
                node.setNext(next.getNext());
                this.decrementCount();
                return nextData;
            }
        }
        // if no node was found ,return null
        return null;
    }

    /**
     * Add multiple items into the existing list
     *
     * @param data elements to be added to the list
     */
    public void addAll(E ...data) {
        for(E d : data) {
            this.add(d);
        }
    }

    /**
     * Merges two array lists
     *
     * @param list the list being merged into the existing list
     */
    public void merge(LinkedList<E> list) {
        Node<E> lastNode = list.findLast();
        lastNode.setNext(this.getHead());
        this.setHead(list.getHead());
    }

    /**
     * Find and return node by the data stored in the node
     *
     * @param data the data being searched for
     * @return Generic object E
     */
    public E find(E data) {
        for (E n : this) {
            if (n.equals(data)) {
                return n;
            }
        }
        return null;
    }

    /**
     * Find the tail of the linked list
     *
     * @return Generic object E
     */
    public Node<E> findLast() {
        if(this.size() == 0) return null;

        Node <E> current = this.getHead();

        while(current.getNext() != null) {
            current = current.getNext();
        }

        return current;
    }

    /**
     * Check if the linked list is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (this.size() == 0 && this.getHead() == null);
    }

    /**
     * Reset the linked list
     *
     */
    public void reset() {
        this.setHead(null);
        this.setCount(0);
    }

    /**
     * Increase the count field, so as to keep track of the number
     * of elements stored in the link least
     */
    private void incrementCount() {
        ++this.count;
    }

    /**
     * Decrease the count field, so as to keep track of the number
     * of elements stored in the link least
     */
    private void decrementCount() {
        --this.count;
    }

    /**
     * Alias for get count
     *
     * @return int (count or number of elements in linked list)
     */
    public int size() {
        return this.getCount();
    }

    /**
     * Get the node stored at the head
     *
     * @return Node<E> (the element stored at the head of the linked list
     */
    public Node<E> getHead() {
        return this.head;
    }

    /**
     * Get the count, i.e. the number of elements stored in the linked list
     *
     * @return count
     */
    private int getCount() {
        return this.count;
    }

    /**
     * Set the head to the specified Node
     * @param head (the node that will become the new head)
     */
    private void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * Set the count field. This method is generally used
     * to initialize the count to 0
     */
    private void setCount(int count) {
        this.count = count;
    }

    /**
     * Return custom iterator that can be used
     * to loop through the elements in the
     * linked list
     *
     * @return new Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<E>(this.getHead());
    }

    /**
     * Override the toString to get the state of the object in string form
     *
     * @return String of the state of the object
     */
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


}
