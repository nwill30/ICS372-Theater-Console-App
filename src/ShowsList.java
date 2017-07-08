import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sam on 7/8/2017.
 */
public class ShowsList extends ItemList<Show, String> {
    private List currentShowings = new LinkedList();
    private static ShowsList showsList;

    /**
     * Singleton private constructor
     */
    private ShowsList(){
    }

    public static ShowsList instance(){
        if (showsList == null){
            return (showsList = new ShowsList());
        }
        else
            return showsList;

    }

    /**
     * Checks if a given show title exists
     * @param title The title of the show to search for
     * @return The show object if found
     */
    public Show search(String title){
        return super.search(title);
    }

    /**
     * Inserts a show into the collection
     * @param show the show to be inserted
     * @return return true if insert successful
     */
    public boolean insertShow(Show show){
        return super.add(show);
    }

    public boolean removeShow(Show show){
        show = search(show.getTitle());
        if(show == null){
            return false;
        }
        else{
            return super.remove(show);
        }
    }

    /**
     * Supports serialization
     * @param output the stream to be written to
     */
    private void writeObject(ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(showsList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Supports serialization
     *  @param input the stream to be read from
     */
    private void readObject(ObjectInputStream input) {
        try {
            if (showsList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (showsList == null) {
                    showsList = (ShowsList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Returns the collection as a String
     * 
     */
    @Override
    public String toString() {
        return currentShowings.toString();
    }

    /**
     * Returns an Iterator of the current showings
     * @return iterator of the collection
     */
    public Iterator getShowings(){
        return super.iterator();
    }
}


