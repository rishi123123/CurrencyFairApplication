package Model;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import Exception.MessageDateFormatException;

public class Message {
    @NotNull
    long userId;

    @NotNull(
            message = "Currency From field is empty"
    )
    String currencyFrom;

    @NotNull(
            message = "Currency To is empty"
    )
    String currencyTo;

    @NotNull(
            message = "Amount sell cannot be empty"
    )
    double amountSell;

    @NotNull(
            message = "Amount buy cannot be empty"
    )
    double amountBuy;

    @NotNull(
            message = "Rate buy cannot be empty"
    )
    double rate;

    @NotNull(
            message = "TimePlaced cannot be empty"
    )

    Date timePlaced;
    @NotNull(
            message = "Originating country cannot be empty"
    )
    String originatingCountry;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getAmoutSell() {
        return amountSell;
    }

    public void setAmoutSell(double amoutSell) {
        this.amountSell = amoutSell;
    }

    public double getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(double amountBuy) {
        this.amountBuy = amountBuy;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public static Date convertStringToDate(String dateString) throws MessageDateFormatException {
        Date formatteddate = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
        try {
            formatteddate = df.parse(dateString);
            System.out.println("Formatted date" + formatteddate);
        } catch (ParseException ex) {
            throw new MessageDateFormatException("Message Format is not the expected format : dd-MMM-yy HH:mm:ss");
        }
        return formatteddate;
    }

    public static Message toMessage(MessageCreateRequest mrc) throws MessageDateFormatException {
        try {
            Message message = new Message();
            message.setUserId(mrc.getUserId());
            message.setCurrencyFrom(mrc.getCurrencyFrom());
            message.setCurrencyTo(mrc.getCurrencyTo());
            message.setAmountBuy(mrc.getAmountBuy());
            message.setAmoutSell(mrc.getAmountSell());
            message.setOriginatingCountry(mrc.getOriginatingCountry());
            message.setRate(mrc.getRate());
            Date d = convertStringToDate(mrc.getTimePlaced());
            if (d != null) {
                message.setTimePlaced(d);
            } else {
                return null;
            }
            return message;
        } catch (MessageDateFormatException messageDateFormatException) {
            throw messageDateFormatException;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Message m = (Message) o;
        return  this.amountBuy == m.amountBuy &&
                this.amountSell == m.amountSell &&
                this.currencyFrom == m.currencyFrom &&
                this.currencyTo == m.currencyTo &&
                this.originatingCountry == m.originatingCountry &&
                this.rate == m.rate;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.userId).hashCode();
    }
}
