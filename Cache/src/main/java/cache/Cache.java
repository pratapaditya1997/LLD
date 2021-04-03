package cache;

import cache.exceptions.NotFoundException;
import cache.exceptions.StorageFullException;
import cache.policies.EvictionPolicy;
import cache.storage.Storage;

public class Cache<K,V> {
    private final EvictionPolicy<K> evictionPolicy;
    private final Storage<K, V> storage;

    public Cache(EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(K key, V value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.registerKeyAccess(key);
        } catch (StorageFullException e) {
            System.out.println("Storage is full. Trying to evict...");
            K keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("unexpected state. storage full and no key to evict");
            }
            this.storage.remove(keyToRemove);
            System.out.println("creating space by evicting item: " + keyToRemove);
            put(key, value);
        }
    }

    public V get(K key) {
        try {
            V value = this.storage.get(key);
            this.evictionPolicy.registerKeyAccess(key);
            return value;
        } catch (NotFoundException e) {
            System.out.println("tried to access non-existent key");
            return null;
        }
    }
}
