package Model;

import DAO.MessageDAOImpl;
import Exception.MessageDateFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageTest {

    @Mock
    Message message;

    @Mock
    MessageDAOImpl messageDAOMock;

    @Mock
    MessageCreateRequest messageCreateRequest;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testToMessageSuccess() {
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
            formatteddate = df.parse(mcr.getTimePlaced());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(formatteddate);
        messageObject.setOriginatingCountry("FR");

        Message messageObject1 = new Message();
        messageObject1.setUserId(134256);
        messageObject1.setCurrencyFrom("AUD");
        messageObject1.setCurrencyTo("INR");
        messageObject1.setAmountBuy(1000);
        messageObject1.setAmoutSell(1000);
        messageObject1.setRate(0.01);
        messageObject1.setTimePlaced(formatteddate);
        messageObject1.setOriginatingCountry("FR");

        try {
            Message returnedMessageObject = Message.toMessage(mcr);
            assertEquals(messageObject,returnedMessageObject);
        } catch (MessageDateFormatException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = MessageDateFormatException.class)
    public void testToMessageFailure() throws MessageDateFormatException {
        MessageCreateRequest mcr= new MessageCreateRequest();
        mcr.setUserId(134256);
        mcr.setCurrencyFrom("AUD");
        mcr.setCurrencyTo("INR");
        mcr.setAmountBuy(1000);
        mcr.setAmoutSell(1000);
        mcr.setRate(0.01);
        mcr.setTimePlaced("15-10-18 12:14:00");
        mcr.setOriginatingCountry("FR");
        try {
            Message returnedMessageObject = Message.toMessage(mcr);
        } catch (MessageDateFormatException e) {
            throw new MessageDateFormatException("Blah");
        }
    }
}