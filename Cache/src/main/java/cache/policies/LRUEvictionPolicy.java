package cache.policies;

import common.DoublyLinkedList;
import common.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private DoublyLinkedList<K> dll;
    private Map<K, DoublyLinkedListNode<K>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void registerKeyAccess(K key) {
        if (mapper.containsKey(key)) {
            dll.deleteNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<K> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public K evictKey() {
        DoublyLinkedListNode<K> first = dll.getFirstNode();
        if (first == null) {
            return null;
        }
        dll.deleteNode(first);
        return first.getElement();
    }
}
