package Model;

import javax.validation.constraints.NotNull;

public class MessageCreateRequest {
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

    String timePlaced;
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

    public double getAmountSell() {
        return amountSell;
    }

    public void setAmoutSell(double amountSell) {
        this.amountSell = amountSell;
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

    public String getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
}
