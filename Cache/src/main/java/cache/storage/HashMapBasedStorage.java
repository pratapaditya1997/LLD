package cache.storage;

import cache.exceptions.NotFoundException;
import cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<K,V> implements Storage<K,V> {
    Map<K, V> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }

    @Override
    public void add(K key, V value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Capacity Full....");
        storage.put(key, value);
    }

    @Override
    public void remove(K key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " does not exist in cache");
        storage.remove(key);
    }

    @Override
    public V get(K key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " does not exist in cache");
        return storage.get(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
