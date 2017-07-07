import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by z077391 on 6/20/2017.
 */
public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Theater theater;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int ADD_CARD = 6;
    private static final int REMOVE_CARD = 7;
    private static final int LIST_CUSTOMERS = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_SHOWS = 10;
    private static final int STORE_DATA = 11;
    private static final int RETRIEVE_DATA = 12;
    private static final int SELL_REGULAR_TICKETS = 13;
    private static final int SELL_ADVANCE_TICKETS = 14;
    private static final int SELL_STUDENT_ADVANCE_TICKETS = 15;
    private static final int PAY_CLIENT = 16;
    private static final int PRINT_TICKETS = 17;
    private static final int HELP = 18;

    /**
     * The method to start the app. Simply calls process()
     *
     * @param args not used
     */
    public static void main(String[] args) {
        UserInterface.instance().process();
    }

    private void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case REMOVE_CLIENT:
                    removeClient();
                    break;
                case LIST_CLIENTS:
                    getClients();
                    break;
                case ADD_CUSTOMER:
                    addCustomer();
                    break;
                case REMOVE_CUSTOMER:
                    removeCustomer();
                    break;
                case LIST_CUSTOMERS:
                    listCustomers();
                    break;
                case ADD_CARD:
                    addCard();
                    break;
                case REMOVE_CARD:
                    removeCard();
                    break;
                case ADD_SHOW:
                    addShow();
                    break;
                case LIST_SHOWS:
                    listShows();
                    break;
                case STORE_DATA:
                    storeData();
                    break;
                case RETRIEVE_DATA:
                    retrieve();
                    break;
                case SELL_REGULAR_TICKETS:
                    sellRegularTickets();
                    break;
                case SELL_ADVANCE_TICKETS:
                    sellAdvanceTickets();
                    break;
                case SELL_STUDENT_ADVANCE_TICKETS:
                    sellStudentAdvanceTickets();
                    break;
                case PAY_CLIENT:
                    payClient();
                    break;
                case HELP:
                    help();
                    break;
            }
        }
    }


    /**
     * Made private for singleton pattern.
     * Conditionally looks for any saved data. Otherwise, it gets
     * a singleton Theater object.
     */
    private UserInterface() {
        if (yesOrNo("Look for saved data and user it?")) {
            retrieve();
        } else {
            theater = Theater.instance();
        }
    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * Method to be called for retrieving saved data.
     * Uses the appropriate Library method for retrieval.
     */
    private void retrieve() {
        try {
            Theater tempTheater = Theater.retrieve();
            if (tempTheater != null) {
                System.out.println(" The theater has been successfully retrieved from the file TheaterData \n");
                theater = tempTheater;
            } else {
                System.out.println("File doesn't exist; creating new Theater");
                theater = Theater.instance();
            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Converts the string to a number
     *
     * @param prompt the string for prompting
     * @return the integer corresponding to the string
     */
    public int getNumber(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     *
     * @param prompt the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }

    /**
     * Prompts for a command from the keyboard
     *
     * @return a valid command
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     *
     * @param prompt The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    /**
     * Gets a token after prompting
     *
     * @param prompt - whatever the user wants as prompt
     * @return - the token from the keyboard
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Displays the help screen
     */
    public void help() {
        System.out.println("Enter a number between 0 and 18 as explained below:");
        System.out.println(EXIT + " to Exit");
        System.out.println(ADD_CLIENT + " to add client(s)");
        System.out.println(REMOVE_CLIENT + " to  remove client(s)");
        System.out.println(LIST_CLIENTS + " to  print clients");
        System.out.println(ADD_CUSTOMER + " to  add customer(s)");
        System.out.println(REMOVE_CUSTOMER + " to  remove customer(s)");
        System.out.println(ADD_CARD + " to  add cards");
        System.out.println(REMOVE_CARD + " to  remove card(s)");
        System.out.println(LIST_CUSTOMERS + " to  print customer(s)");
        System.out.println(ADD_SHOW + " to  add show/play(s)");
        System.out.println(LIST_SHOWS + " to  print shows");
        System.out.println(STORE_DATA + " to  save data");
        System.out.println(RETRIEVE_DATA + " to  retrieve data");
        System.out.println(SELL_REGULAR_TICKETS + " for sell regular tickets");
        System.out.println(SELL_ADVANCE_TICKETS + " for sell advance tickets");
        System.out.println(SELL_STUDENT_ADVANCE_TICKETS + " for sell student advance tickets");
        System.out.println(PAY_CLIENT + " for pay clients");
        System.out.println(PRINT_TICKETS + " for printing tickets for a certain day");
        System.out.println(HELP + " for help");

    }

    /**
     * Method to be called for adding a client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the client.
     */
    public void addClient() {
        String name = getToken("Enter member name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = theater.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Could not add member");
        }
        System.out.println(result.toString());
    }

    /**
     * Method to be called for removing client.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for removing client.
     */
    public void removeClient() {
        int result;
        do {
            String clientID = getToken("Enter client id");
            result = theater.removeClient(clientID);
            switch (result) {
                case Theater.CLIENT_NOT_FOUND:
                    System.out.println("No such client in Theater");
                    break;
                case Theater.ACTIVE_SHOW:
                    System.out.println("Client has an active show(s)");
                    break;
                case Theater.OPERATION_FAILED:
                    System.out.println("Client could not be removed");
                    break;
                case Theater.OPERATION_COMPLETED:
                    System.out.println("Client has been removed");
                    break;
                default:
                    System.out.println("An error has occurred");
            }
            if (!yesOrNo("Remove more clients?")) {
                break;
            }
        } while (true);
    }

    /**
     * Method called to diaplay all Clients
     */
    public void getClients() {
        Iterator result = theater.getClients();
        if (result == null) {
            System.out.println("No clients available");
        } else {
            while (result.hasNext()) {
                Client client = (Client) result.next();
                System.out.println(client.toString());
            }
        }
    }

    /**
     * Method to be called for adding a customer.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for adding the customer.
     */
    public void addCustomer() {
        String name = getToken("Enter customer name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        String creditCardNumber = getToken("Enter Credit Card number");
        String creditCardExp = getToken("Enter Credit Card expiration date");
        Customer result;
        result = theater.addCustomer(name, address, phone, creditCardNumber, creditCardExp);
        if (result == null) {
            System.out.println("Could not add customer");
        }
        System.out.println(result);
    }

    /**
     * Method to be called for removing customer.
     * Prompts the user for the appropriate values and
     * uses the appropriate Theater method for removing customer.
     */
    public void removeCustomer() {
        int result;
        do {
            String customerID = getToken("Enter customer id");
            result = theater.removeCustomer(customerID);
            switch (result) {
                case Theater.CUSTOMER_NOT_FOUND:
                    System.out.println("No such customer in Theater");
                    break;
                case Theater.OPERATION_FAILED:
                    System.out.println("Customer could not be removed");
                    break;
                case Theater.OPERATION_COMPLETED:
                    System.out.println("Customer has been removed");
                    break;
                default:
                    System.out.println("An error has occurred");
            }
            if (!yesOrNo("Remove more customers?")) {
                break;
            }
        } while (true);
    }


    /**
     * Method called to diaplay all Clients
     */
    public void listCustomers() {
        Iterator result = theater.getCustomers();
        if (result == null) {
            System.out.println("No customers available");
        } else {
            while (result.hasNext()) {
                Customer customer = (Customer) result.next();
                System.out.println(customer.toString());
            }
        }
    }

    /**
     * Method to be called for adding a show.
     * Prompts the user for the appropriate values and
     * uses the appropriate Client method for adding a show.
     */
    public void addShow() {
        String showTitle = getToken("Enter show/play title");
        String clientId = getToken("Enter client id");
        String showDt = getToken("Enter show start date in YYYY-MM-DD format");
        Integer showPeriod = Integer.parseInt(getToken("Enter show period in number of weeks"));
        Double ticketPrice = Double.parseDouble(getToken("Enter show ticket price"));

        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = new SimpleDateFormat(pattern).parse(showDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar showDate = Calendar.getInstance();
        showDate.setTime(date);
        Show result;
        result = theater.addShow(showTitle, showDate, showPeriod, ticketPrice, clientId);
        if (result == null) {
            System.out.println("Could not add show");
        } else {
            System.out.println(result.toString());
        }


    }

    /**
     * Method to be called for listing all shows.
     **/
    public void listShows() {

        Iterator itr = theater.getClients();
        while (itr.hasNext()) {
            Client client = (Client) itr.next();
            List<Show> shows = client.getShows();
            for (Iterator iterator = shows.iterator(); iterator.hasNext(); ) {
                Show show = (Show) iterator.next();
                System.out.println(show.toString());
            }
        }
    }

    public void addCard() {
        String customerId = getToken("Enter customer id");
        Customer customer;
        if (CustomerList.instance().search(customerId) != null) {
            customer = CustomerList.instance().search(customerId);
            String creditCardNumber = getToken("Enter Credit Card Number");
            String creditCardExpirationDate = getToken("Enter Credit Card Expiration Date");
            CreditCard creditCard = new CreditCard(creditCardNumber, creditCardExpirationDate);
            customer.customerAddCard(creditCard);
            System.out.println(customer);
        } else {
            System.out.println("Customer does not exist");
        }

    }

    public void removeCard() {
        String customerId = getToken("Enter customer id");
        Customer customer;
        if (CustomerList.instance().search(customerId) != null) {
            customer = CustomerList.instance().search(customerId);
            if (customer.customerCCListSize() > 1) {
                String ccNumToRemove = getToken("Enter the credit card number for removal");
                customer.customerRemoveCard(CreditCardList.instance().search(ccNumToRemove));
                System.out.println(customer);
            } else
                System.out.println("There must be at least 1 Credit Card on file");
        }

    }

    public void storeData() {
        if (Theater.save())
            System.out.println("Save successful");
        else
            System.out.println("Save failed");

    }

    /**
     * Method to call for selling Regular tickets
     */
    public void sellRegularTickets() {
        String customerId = getToken("Enter customer id");
        Customer customer = theater.getCustomerList().search(customerId);
        if (customer == null) {
            System.out.println("No customer found. Sell ticket is not successful.");
            return;
        }

        String showId = getToken("Enter show id");
        Iterator<Client> clientListItr = theater.getClients();

        boolean showFound = false;
        Client cl = null;
        Show show = null;
        while (clientListItr.hasNext() && showFound == false) {
            cl = clientListItr.next();
            Iterator<Show> showListItr = cl.getShows().iterator();
            while (showListItr.hasNext() && showFound == false) {
                Show sh = showListItr.next();
                if (sh.getId().equals(showId)) {
                    show = sh;
                    showFound = true;
                }
            }
        }


        if (showFound == false) {
            System.out.println("No show found. Sell ticket is not successful.");
            return;
        }


        String creditCardNumber = getToken("Enter Credit Card number");
        String showDt = getToken("Enter show date in YYYY-MM-DD format");
        String regularTicketQuantity = getToken("Enter Regular Ticket Quantity");

        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = new SimpleDateFormat(pattern).parse(showDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar showDate = Calendar.getInstance();
        showDate.setTime(date);

        Integer quantity = Integer.parseInt(regularTicketQuantity);
        Double totalPrice = 0.0;


        totalPrice = show.getTicketPrice() * quantity;

        Ticket ticket = new Ticket(totalPrice, quantity, showId, showDate, customerId, creditCardNumber, "REGULAR");

        //Add ticket to the ticket list for the thetre
        theater.getTicketsList().add(ticket);

        //Add ticket to the ticket list of the customer
        customer.getCustomerTickets().add(ticket);

        cl.setBalance(cl.getBalance() + totalPrice / 2);
        System.out.println(ticket.toString());


    }

    /**
     * Method to call for selling Advanced tickets
     */
    public void sellAdvanceTickets() {
        String customerId = getToken("Enter customer id");
        Customer customer = theater.getCustomerList().search(customerId);
        if (customer == null) {
            System.out.println("No customer found. Sell ticket is not successful.");
            return;
        }

        String showId = getToken("Enter show id");
        Iterator<Client> clientListItr = theater.getClients();

        boolean showFound = false;
        Client cl = null;
        Show show = null;
        while (clientListItr.hasNext() && showFound == false) {
            cl = clientListItr.next();
            Iterator<Show> showListItr = cl.getShows().iterator();
            while (showListItr.hasNext() && showFound == false) {
                Show sh = showListItr.next();
                if (sh.getId().equals(showId)) {
                    show = sh;
                    showFound = true;
                }
            }
        }


        if (showFound == false) {
            System.out.println("No show found. Sell ticket is not successful.");
            return;
        }

        String showDt = getToken("Enter show date in YYYY-MM-DD format");


        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = new SimpleDateFormat(pattern).parse(showDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar showDate = Calendar.getInstance();
        showDate.setTime(date);

        Date today = Calendar.getInstance().getTime();
        if (date.compareTo(today) <= 0) {
            System.out.println("Advance tickets can only be purchased atleast oneday in advance");
            return;
        }

        String creditCardNumber = getToken("Enter Credit Card number");
        String regularTicketQuantity = getToken("Enter Regular Ticket Quantity");

        Integer quantity = Integer.parseInt(regularTicketQuantity);
        Double totalPrice = 0.0;


        totalPrice = show.getTicketPrice() * quantity * 0.7;

        Ticket ticket = new Ticket(totalPrice, quantity, showId, showDate, customerId, creditCardNumber, "ADVANCED");

        //Add ticket to the ticket list for the thetre
        theater.getTicketsList().add(ticket);

        //Add ticket to the ticket list of the customer
        customer.getCustomerTickets().add(ticket);

        cl.setBalance(cl.getBalance() + totalPrice / 2);
        System.out.println(ticket.toString());
    }

    /**
     * Method to call for selling Advanced tickets
     */
    public void sellStudentAdvanceTickets() {

        System.out.println("Must show valid student id");
        String customerId = getToken("Enter customer id");
        Customer customer = theater.getCustomerList().search(customerId);
        if (customer == null) {
            System.out.println("No customer found. Sell ticket is not successful.");
            return;
        }

        String showId = getToken("Enter show id");
        Iterator<Client> clientListItr = theater.getClients();

        boolean showFound = false;
        Client cl = null;
        Show show = null;
        while (clientListItr.hasNext() && showFound == false) {
            cl = clientListItr.next();
            Iterator<Show> showListItr = cl.getShows().iterator();
            while (showListItr.hasNext() && showFound == false) {
                Show sh = showListItr.next();
                if (sh.getId().equals(showId)) {
                    show = sh;
                    showFound = true;
                }
            }
        }


        if (showFound == false) {
            System.out.println("No show found. Sell ticket is not successful.");
            return;
        }

        String showDt = getToken("Enter show date in YYYY-MM-DD format");


        Date date = null;
        String pattern = "yyyy-MM-dd";
        try {
            date = new SimpleDateFormat(pattern).parse(showDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar showDate = Calendar.getInstance();
        showDate.setTime(date);

        Date today = Calendar.getInstance().getTime();
        if (date.compareTo(today) <= 0) {
            System.out.println("Advance tickets can only be purchased atleast oneday in advance");
            return;
        }

        String creditCardNumber = getToken("Enter Credit Card number");
        String regularTicketQuantity = getToken("Enter Regular Ticket Quantity");

        Integer quantity = Integer.parseInt(regularTicketQuantity);
        Double totalPrice = 0.0;


        totalPrice = show.getTicketPrice() * quantity * 0.7 * 0.5;

        Ticket ticket = new Ticket(totalPrice, quantity, showId, showDate, customerId, creditCardNumber, "ADVANCED");

        //Add ticket to the ticket list for the thetre
        theater.getTicketsList().add(ticket);

        //Add ticket to the ticket list of the customer
        customer.getCustomerTickets().add(ticket);

        cl.setBalance(cl.getBalance() + totalPrice / 2);
        System.out.println(ticket.toString());
    }

    public void payClient() {
        String clientId = getToken("Enter Client Id");
        boolean clientFound = false;
        Client cl = null;

        Iterator<Client> clientListItr = theater.getClients();


        while (clientListItr.hasNext() && clientFound == false) {
            cl = clientListItr.next();
            if (cl.getId().equals(clientId)) {
                clientFound = true;
            }
        }

        if (!clientFound) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Client balance: " + cl.getBalance());

        String amount = getToken("Enter the amount to be paid: ");

        Double amountToBePaid = Double.parseDouble(amount);

        while (amountToBePaid > cl.getBalance()) {
            System.out.println("Max amout can be paid is: " + cl.getBalance());
            amount = getToken("Enter the amount to be paid: ");
            amountToBePaid = Double.parseDouble(amount);
        }

        cl.setBalance(cl.getBalance() - amountToBePaid);
        System.out.println("Paid : " + amountToBePaid + " Client's new balance: " + cl.getBalance());


    }


}


