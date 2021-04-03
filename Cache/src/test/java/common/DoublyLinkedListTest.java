package common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    @Test
    public void testDLLInsertion() {
        DoublyLinkedListNode<Integer> node1 = new DoublyLinkedListNode<>(1);
        DoublyLinkedListNode<Integer> node2 = new DoublyLinkedListNode<>(2);
        DoublyLinkedListNode<Integer> node3 = new DoublyLinkedListNode<>(3);
        DoublyLinkedListNode<Integer> node4 = new DoublyLinkedListNode<>(4);

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.addNodeAtLast(node1);
        verifyDLL(dll, Arrays.asList(1));

        dll.addNodeAtLast(node2);
        verifyDLL(dll, Arrays.asList(1, 2));

        dll.addNodeAtLast(node3);
        verifyDLL(dll, Arrays.asList(1, 2, 3));

        dll.addNodeAtLast(node4);
        verifyDLL(dll, Arrays.asList(1, 2, 3, 4));

        dll.addElementAtLast(5);
        verifyDLL(dll, Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void testDLLDeletion() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        DoublyLinkedListNode<Integer> node1 = dll.addElementAtLast(1);
        DoublyLinkedListNode<Integer> node2 = dll.addElementAtLast(2);
        DoublyLinkedListNode<Integer> node3 = dll.addElementAtLast(3);
        DoublyLinkedListNode<Integer> node4 = dll.addElementAtLast(4);
        DoublyLinkedListNode<Integer> node5 = dll.addElementAtLast(5);

        verifyDLL(dll, Arrays.asList(1, 2, 3, 4, 5));

        dll.deleteNode(node1);
        verifyDLL(dll, Arrays.asList(2, 3, 4, 5));

        dll.deleteNode(node5);
        verifyDLL(dll, Arrays.asList(2, 3, 4));

        dll.deleteNode(node3);
        verifyDLL(dll, Arrays.asList(2, 4));

        dll.deleteNode(null);
        verifyDLL(dll, Arrays.asList(2, 4));
    }

    private void verifyDLL(DoublyLinkedList<Integer> dll, List<Integer> expectedElements) {
        assertEquals(expectedElements.get(expectedElements.size() - 1), dll.getLastNode().getElement());
        assertEquals(expectedElements.get(0), dll.getFirstNode().getElement());

        DoublyLinkedListNode<Integer> currentNode = dll.getFirstNode();
        for (Integer expected: expectedElements) {
            assertNotNull(currentNode);
            assertEquals(expected, currentNode.getElement());
            currentNode = currentNode.getNext();
        }
        assertNull(currentNode.next);
    }
}