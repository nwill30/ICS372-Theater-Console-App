/**
 * Created by tsma on 7/6/2017.
 */
public interface Matchable<K> {
    /**
     * Checks whether an item's key matches the given key.
     *
     * @param key
     *            the key value
     * @return true iff the item's key matches the given key
     */
    public boolean matches(K key);
}
