package common;

import common.exceptions.InvalidElementException;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
    DoublyLinkedListNode<E> dummyHead;
    DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new DoublyLinkedListNode<>(null);
        dummyTail = new DoublyLinkedListNode<>(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void deleteNode(DoublyLinkedListNode<E> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException("null element");
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    public DoublyLinkedListNode getFirstNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}
