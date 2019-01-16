package tests;

import collections.LinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void newLinkedListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void resetLinkedListIsEmpty() {
        list.add(1);
        list.reset();
        assertTrue(list.isEmpty());
    }

    @Test
    void listWithThreeNodesHasSizeThree() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.size(), 3);
    }

    @Test
    void findNodeByData() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.find(2), new Integer(2));
    }

    @Test
    void deleteMethodReturnsDeletedNode() {
        list.add(1);
        list.add(2);
        list.add(3);
        Integer test = list.find(2);
        Integer deleted = list.delete(2);
        assertEquals(test, deleted);
    }

    @Test
    void deletedNodeNoLongerInList() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.delete(3);
        assertNull(list.find(3));
    }

    @Test
    void findLastMethodFindsTheElementThatWasAddedFirst() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.findLast().getData(), new Integer(1));
    }

    @Test
    void multipleItemsCanBeAddedToAList() {
        list.addAll(6,7,8,9);
        assertEquals(list.findLast().getData(), new Integer(6));
        assertEquals(list.getHead().getData(), new Integer(9));
    }


}