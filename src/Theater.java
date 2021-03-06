import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by z077391 on 6/20/2017.
 */
public class Theater implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final int CLIENT_NOT_FOUND = 1;
    public static final int ACTIVE_SHOW = 3;
    public static final int OPERATION_COMPLETED = 7;
    public static final int OPERATION_FAILED = 8;
    public static final int CUSTOMER_NOT_FOUND = 5;

    private ClientList clientList;
    private CreditCardList creditCardList;
    private CustomerList customerList;
    private List<Ticket> ticketsList;
    private static Theater theater;



    /**
     * Private for the singleton pattern
     * Creates the catalog and member collection objects
     */
    private Theater() {

        clientList = ClientList.instance();
        customerList = CustomerList.instance();
        creditCardList = CreditCardList.instance();
        ticketsList = new LinkedList<Ticket>();

    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static Theater instance() {
        if (theater == null) {
            ClientIdServer.instance(); // instantiate all singletons
            return (theater = new Theater());
        } else {
            return theater;
        }
    }

    /**
     * Retrieves a deserialized version of the theater from disk
     * @return a Theater object
     */
    public static Theater retrieve()
    {
        try {
            FileInputStream file = new FileInputStream("TheaterData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            ClientIdServer.retrieve(input);
            return theater;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    /**
     * Organizes the operations for adding a client
     * @param name client name
     * @param address client address
     * @param phone client phone
     * @return the Client object created
     */
    public Client addClient(String name, String address, String phone)
    {
        Client client = new Client(name, address, phone);
        if (clientList.insertClient(client)) {
            return (client);
        }
        return null;
    }

    /**
     * Removes a specific book from the catalog
     * @param clientId id of the book
     * @return a code representing the outcome
     */
    public int removeClient(String clientId) {
        Client client = clientList.search(clientId);
        if (client == null) {
            return(CLIENT_NOT_FOUND);
        }
        if (client.hasShow()) {
            return(ACTIVE_SHOW);
        }
        if (clientList.removeClient(clientId)) {
            return (OPERATION_COMPLETED);
        }
        return (OPERATION_FAILED);
    }

    public Client getClient(String clientId) {
        return(clientList.search(clientId));
    }

    /**
     * Method called to retreive the iterator client list
     * @return a list iterator of the ClientList
     * */
    public Iterator getClients()
    {
        return clientList.getClients();
    }


    /**
     * Method called to retreive the iterator customer list
     * @return a list iterator of the CustomerList
     * */
    public Iterator getCustomers()
    {
        return customerList.getCustomers();
    }


    public Customer addCustomer(String name, String address, String phone, String creditCardNumber, String creditCardExp)
    {
        if(creditCardList.search(creditCardNumber)!=null){
            return null;
        }else{
            CreditCard creditCard = addCreditCard(creditCardNumber,creditCardExp);
            Customer customer = new Customer(name,address,phone,creditCard);
            if (customerList.insertCustomer(customer)) {
                return (customer);
            }
            return null;
        }
    }

    /**
     * Removes a specific customer
     * @param customerId id of the book
     * @return a code representing the outcome
     */
    public int removeCustomer(String customerId) {
        Customer customer = customerList.search(customerId);
        if (customer == null) {
            return(CUSTOMER_NOT_FOUND);
        }
        if (customerList.removeCustomer(customerId)) {
            return (OPERATION_COMPLETED);
        }
        return (OPERATION_FAILED);
    }

    private CreditCard addCreditCard(String creditCardNumber, String creditCardExp) {
        CreditCard creditCard = new CreditCard(creditCardNumber,creditCardExp);
        if(creditCardList.insertCreditCard(creditCard))
        {
            return (creditCard);
        }
        return null;
    }

    public void addCreditCardExistingCustomer(String customerId, String creditCardNum, String creditCardExp){
        Customer customer;
        CreditCard creditCard = new CreditCard(creditCardNum,creditCardExp);
        if(creditCardList.search(creditCard.getCreditCardNumber()) == null){
            if(customerList.search(customerId) != null){
                customer = customerList.search(customerId);
                customer.customerAddCard(creditCard);
                System.out.println(customer);
            }
            else{
                System.out.println("Customer does not exist");
            }
        }
        else{
            System.out.println("Card already exists");
        }
    }

    public void removeCard(String customerID, String creditCardNum){
        Customer customer;
        if (customerList.search(customerID) != null) {
            customer = CustomerList.instance().search(customerID);
            if (customer.customerCCListSize() > 1) {
                customer.customerRemoveCard(creditCardList.search(creditCardNum));
                System.out.println(customer);
            }else
                System.out.println("There must be at least 1 Credit Card on file");
        }else{
            System.out.println("Customer does not exist");
        }

    }

    public Show addShow(String showTitle, Calendar showDate, Integer showPeriod, Double ticketPrice,String clientId)
    {
        Show show = new Show(showTitle,showDate,showPeriod,ticketPrice);
        Client client = theater.getClient(clientId);
        if (client == null) {
            System.out.println("Specified client doesn't exist");
            return null;
        }
            boolean result = false;
            //Iterator iterator = clientList.getClients();
//            for(Iterator<ClientList> clientListIterator = clientList.getClients(); clientListIterator.hasNext();){
//                Client currentClient = clientListIterator.next();
//            }
            client.addShow(show);
            System.out.println("Show added for the client");
            System.out.println(client.toString());
            return show;


    }

    public static boolean save() {
        try{
            FileOutputStream file = new FileOutputStream("TheaterData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(theater);
            output.writeObject(ClientIdServer.instance());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(theater);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            input.defaultReadObject();
            if(theater == null){
                theater = (Theater) input.readObject();
            }else{
                input.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }

}
