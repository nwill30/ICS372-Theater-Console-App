import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by teche on 6/28/2017.
 */
public class StudentTicket extends Ticket implements Serializable {
    public StudentTicket(Double totalPrice, int quantity, String showId, Calendar showDate, String customerId, String creditCardNumber, String type) {
        super(totalPrice, quantity, showId, showDate, customerId, creditCardNumber, type);
    }
}
