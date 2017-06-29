import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by teche on 6/21/2017.
 */
public class Show implements Serializable{

    private String title;
    private String id;
    private Calendar date;
    private int period;
    private int ticketPrice;
    private static final long serialVersionUID = 1L;
    private static final String SHOW_STRING = "SH";

    /**
     * Represents a single member
     * @param date date the show will start
     * @param title name of the show
     * @param period the number of weeks the show will run
     * @param ticketPrice the price of the shows ticket
     * */
    public Show(String title, Calendar date, int period, int ticketPrice)
    {
        this.title = title;
        this.date = date;
        this.period = period;
        this.ticketPrice = ticketPrice;
        id = SHOW_STRING + (ClientIdServer.instance()).getId();
    }
    /**
     *returns the end date of the show
     * @return the end date of the show
     * */
    public String getEndDate()
    {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        Calendar endDate = date;
        endDate.add(Calendar.DATE, period);
        return dateFormat.format(endDate);
    }
    /**
     * Getter for title
     * @return the title of the shoe
     * */
    public String getTitle() {
        return title;
    }
    /**
     * Getter for showId
     * @return the id of the requested show
     * */
    public String getId() {
        return id;
    }
    /**
     * Getter for the show date
     * @return the show start date
     * */
    public Calendar getDate() {
        return date;
    }
    /**
     * Getter for the show run period
     * @return the number of weeks the show will run
     * */
    public int getPeriod() {
        return period;
    }
    /**
     * Setter for the show period
     * If a period is provided below 0 the period will default to 0
     * @param period the number of weeks the show will run
     * */
    public void setPeriod(int period) {
        if(period<=0)
        {
            this.period = 0;
        }else {
            this.period = period;
        }
    }
    /**
     * Getter for the show regular ticket price
     * @return  the price of a regular ticket
     * */
    public int getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatteDate = dateFormat.format(date.getTime());
        return "Show{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", date=" + formatteDate +
                ", period=" + period +
                '}';
    }
}
