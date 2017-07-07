import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by bputrevu on 7/6/17.
 */
public class Ticket implements Serializable{

    private String id;
    private Double totalPrice;
    // REGULAR or ADVANCED or STUDENTADVANCED
    private int quantity;
    private String showId;
    private Calendar showDate;
    private String customerId;
    private String creditCardNumber;
    private String type;


    public Ticket(Double totalPrice, int quantity, String showId, Calendar showDate, String customerId, String creditCardNumber, String type) {
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.showId = showId;
        this.showDate = showDate;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.type = type;
        id = "TI" + (ClientIdServer.instance()).getId();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Calendar getShowDate() {
        return showDate;
    }

    public void setShowDate(Calendar showDate) {
        this.showDate = showDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                ", showId='" + showId + '\'' +
                ", showDate=" + showDate +
                ", customerId='" + customerId + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
