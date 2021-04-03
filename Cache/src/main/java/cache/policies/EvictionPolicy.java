package cache.policies;

public interface EvictionPolicy<K> {
    void registerKeyAccess(K key);
    K evictKey();
}
