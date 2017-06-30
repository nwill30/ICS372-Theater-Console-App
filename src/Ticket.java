import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by teche on 6/28/2017.
 */
public abstract class Ticket implements Serializable{

    private String serialNumber;
    private Calendar date;
    private int price;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
