package cache.storage;

import cache.exceptions.NotFoundException;
import cache.exceptions.StorageFullException;

public interface Storage<K,V> {
    void add(K key, V value) throws StorageFullException;
    void remove(K key) throws NotFoundException;
    V get(K key) throws NotFoundException;
}
