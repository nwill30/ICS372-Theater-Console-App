import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Client implements Serializable, Matchable<String> {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;
    private String id;
    private List<Show> shows = new LinkedList();
    private static final String CLIENT_STRING = "CL";
    private double balance;

    /**
     * Represents a single member
     * @param name name of the member
     * @param address address of the member
     * @param phone phone number of the member
     */
    public  Client (String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        id = CLIENT_STRING + (ClientIdServer.instance()).getId();
        balance = 0.0;
    }


    /**
     * Getter method for the id
     * @return returns the clientId*/
    public String getId() {
        return id;
    }

    /**
     * Adds show to the currnet list
     * @param show the new show to be added
     * */
    public void addShow(Show show)
    {
        shows.add(show);
    }

    /**
     * Checks whether there is a show scheduled after the current date
     * @return false iif there is a show
     * */
    public boolean hasShow() {

        /**
        ListIterator iterator = shows.listIterator();
        int i = 0;
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        Calendar date = Calendar.getInstance();
        if(iterator.hasNext())
        {
            i++;
            if(shows.get(i).getEndDate().compareTo(dateFormat.format(date)) >0 )
            {
                return true;
            }
        }
        return false;
         **/

        return(!shows.isEmpty());

    }

    public List<Show> getShows() {
        return shows;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Return string of the Client
     * String contains id, name, address and phone number
     */
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                ", shows=" + shows.toString() +
                ", balance=" + balance +
                '}';
    }


    /**
     * Checks whether an item's key matches the given key.
     *
     * @param key the key value
     * @return true iff the item's key matches the given key
     */
    @Override
    public boolean matches(String key) {
        return id.equals(key);
    }
}
