package cache.policies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUEvictionPolicyTest {
    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @BeforeEach
    void setup() {
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    void noKeyToEvictInitially() {
        assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lruEvictionPolicy.registerKeyAccess(1);
        lruEvictionPolicy.registerKeyAccess(2);
        lruEvictionPolicy.registerKeyAccess(3);
        lruEvictionPolicy.registerKeyAccess(4);
        assertEquals(Integer.valueOf(1), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(2), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(3), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(4), lruEvictionPolicy.evictKey());
    }

    @Test
    void testReaccesingKeyPreventsItFromEviction() {
        lruEvictionPolicy.registerKeyAccess(1);
        lruEvictionPolicy.registerKeyAccess(2);
        lruEvictionPolicy.registerKeyAccess(3);
        lruEvictionPolicy.registerKeyAccess(2);
        lruEvictionPolicy.registerKeyAccess(4);
        lruEvictionPolicy.registerKeyAccess(1);
        lruEvictionPolicy.registerKeyAccess(5);
        assertEquals(Integer.valueOf(3), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(2), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(4), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(1), lruEvictionPolicy.evictKey());
        assertEquals(Integer.valueOf(5), lruEvictionPolicy.evictKey());
    }
}