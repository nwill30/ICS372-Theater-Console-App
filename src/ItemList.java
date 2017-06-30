import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by teche on 6/29/2017.
 */
public class ItemList<T extends  Matchable<K>,K> implements Serializable{
    private static final long SerialVersionUID = 1L;
    private List<T> list = new LinkedList<T>();

    /**
     * Checks whether an item with a given id exsists.
     * @param key the id of the item
     *@return   the item iff the item exists*/
    public T search(K key)
    {
        for (T item : list)
        {
            if (item.matches(key))
            {
                return item;
            }
        }
        return null;
    }

    /****
     * adds an item to the list
     * @param item the item to be added
     * @return true iff the item could be added
     */
    public boolean add(T item)
    {
        return list.add(item);
    }
    /**
     * removes the item from the list
     * @param item the item to be removed
     */
    public boolean remove (T item)
    {
        return list.remove(item);
    }

    /**
     * returns an iterator for the collection
     * @return iterator for the collection
     * */
    public Iterator<T> iterator()
    {
        return list.iterator();
    }

}
