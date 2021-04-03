import cache.Cache;
import cache.exceptions.NotFoundException;
import cache.policies.LRUEvictionPolicy;
import cache.storage.HashMapBasedStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheTest {
    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup() {
        LRUEvictionPolicy<Integer> lruEvictionPolicy = new LRUEvictionPolicy<>();
        HashMapBasedStorage<Integer, Integer> hashMapBasedStorage = new HashMapBasedStorage<>(3);
        cache = new Cache<Integer, Integer>(lruEvictionPolicy, hashMapBasedStorage);
    }

    @Test
    public void getAndAddItemsInCache() {
        cache.put(1, 1);
        cache.put(2, 2);

        Assertions.assertEquals(Integer.valueOf(1), cache.get(1));
        cache.put(3, 3);

        cache.put(4, 4);
        cache.get(2);
    }
}
