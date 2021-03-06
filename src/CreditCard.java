import java.io.Serializable;

/**
 * Created by teche on 6/21/2017.
 */
public class CreditCard implements Serializable, Matchable<String> {
    private String creditCardNumber;
    private String creditCardExp;

    /**
     * Represents a single credit card
     * @param creditCard the card number of the card used
     * @param creditCardExp the experation date of the credit card*/
    public CreditCard(String creditCard, String creditCardExp)
    {
        this.creditCardNumber = creditCard;
        this.creditCardExp = creditCardExp;
    }
    /**
     * Getter for the credit card number
     * @return the cards number
     * */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    /**
     * Getter for the credit card experation date
     * @return the cards exp
     * */
    public String getCreditCardExp() {
        return creditCardExp;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardExp='" + creditCardExp + '\'' +
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
        return getCreditCardNumber().equals(key);
    }
}
