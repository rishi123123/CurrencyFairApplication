package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Exception.MessageDateFormatException;

public class Tester {
    public static void main(String[] args) throws MessageDateFormatException {

        MessageCreateRequest mcr= new MessageCreateRequest();
        mcr.setUserId(134256);
        mcr.setCurrencyFrom("AUD");
        mcr.setCurrencyTo("INR");
        mcr.setAmountBuy(1000);
        mcr.setAmoutSell(1000);
        mcr.setRate(0.01);
        mcr.setTimePlaced("15-JAN-18 12:14:00");
        mcr.setOriginatingCountry("FR");

        Date formatteddate = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
        try {
            System.out.println("Message Create Request"+mcr.getTimePlaced());
            formatteddate = df.parse(mcr.getTimePlaced());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Message messageObject1 = new Message();
        messageObject1.setUserId(134256);
        messageObject1.setCurrencyFrom("AUD");
        messageObject1.setCurrencyTo("INR");
        messageObject1.setAmountBuy(1000);
        messageObject1.setAmoutSell(1000);
        messageObject1.setRate(0.01);
        messageObject1.setTimePlaced(formatteddate);
        messageObject1.setOriginatingCountry("FR");

        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(formatteddate);
        messageObject.setOriginatingCountry("FR");
        Message returnedMessageObject = null;
        try {
            returnedMessageObject = Message.toMessage(mcr);
        } catch (MessageDateFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Returned");
        System.out.println(returnedMessageObject.getTimePlaced());
        System.out.println(returnedMessageObject.getAmountBuy());
        System.out.println(returnedMessageObject.getAmoutSell());
        System.out.println(returnedMessageObject.getCurrencyFrom());
        System.out.println(returnedMessageObject.getCurrencyTo());
        System.out.println(returnedMessageObject.getOriginatingCountry());
        System.out.println(returnedMessageObject.getRate());
        System.out.println(returnedMessageObject.getUserId());


        System.out.println("Messages Object");
        System.out.println(messageObject.getTimePlaced());
        System.out.println(messageObject.getAmountBuy());
        System.out.println(messageObject.getAmoutSell());
        System.out.println(messageObject.getCurrencyFrom());
        System.out.println(messageObject.getCurrencyTo());
        System.out.println(messageObject.getOriginatingCountry());
        System.out.println(messageObject.getRate());
        System.out.println(messageObject.getUserId());


        System.out.println(messageObject.equals(returnedMessageObject));
        System.out.println (
        messageObject.timePlaced == returnedMessageObject.timePlaced &&
                messageObject.amountBuy == returnedMessageObject.amountBuy &&
                messageObject.amountSell == returnedMessageObject.amountSell &&
                messageObject.currencyFrom == returnedMessageObject.currencyFrom &&
                messageObject.currencyTo == returnedMessageObject.currencyTo &&
                messageObject.originatingCountry == returnedMessageObject.originatingCountry &&
                messageObject.rate == returnedMessageObject.rate
        );
    }
}
