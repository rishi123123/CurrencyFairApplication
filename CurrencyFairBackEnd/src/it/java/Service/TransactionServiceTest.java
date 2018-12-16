package Service;

import DAO.MessageDAOImpl;
import Model.Message;
import Model.WindowStatistics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {
    @Mock
    MessageDAOImpl messageDAOMock;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testinsertMessageUnitSuccess() {
        Message message = new Message();
        when(messageDAOMock.insertMessageIntoDB(message))
                .thenReturn(true);
        boolean messageInsert = messageDAOMock.insertMessageIntoDB(message);
        assertTrue(messageInsert);
    }

    @Test
    public void testinsertMessageUnitFailure() {
        Message message = null;
        boolean messageInsert = messageDAOMock.insertMessageIntoDB(message);
        assertFalse(messageInsert);
    }

    @Test
    public void testgetMessagesBasedOnFilter() {
        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(new Date());
        messageObject.setOriginatingCountry("FR");

        Message messageObject1 = new Message();
        messageObject1.setUserId(134256);
        messageObject1.setCurrencyFrom("EUR");
        messageObject1.setCurrencyTo("GBP");
        messageObject1.setAmountBuy(1000);
        messageObject1.setAmoutSell(1000);
        messageObject1.setRate(0.01);
        messageObject1.setTimePlaced(new Date());
        messageObject1.setOriginatingCountry("UK");

        List<Message> messageList = new ArrayList<>();
        messageList.add(messageObject);
        messageList.add(messageObject1);
        String currencyFrom ="EUR";
        String currencyTo = "GBP";
        String originatingCountry = "UK";

        List<Message> messageListExpected = new ArrayList<>();
        messageListExpected.add(messageObject);
        messageListExpected.add(messageObject1);

        when(
                messageDAOMock.getMessageBasedOnFilter(currencyFrom,currencyTo,originatingCountry)
        ).thenReturn(
                messageList
        );
        List<Message> listMessage = messageDAOMock.getMessageBasedOnFilter(currencyFrom,currencyTo,originatingCountry);
        assertEquals(messageListExpected,listMessage);
    }

    @Test
    public void testGetMessages60SecondTimeWindow() {
        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(new Date());
        messageObject.setOriginatingCountry("FR");

        Message messageObject1 = new Message();
        messageObject1.setUserId(134256);
        messageObject1.setCurrencyFrom("EUR");
        messageObject1.setCurrencyTo("GBP");
        messageObject1.setAmountBuy(1000);
        messageObject1.setAmoutSell(1000);
        messageObject1.setRate(0.01);
        messageObject1.setTimePlaced(new Date());
        messageObject1.setOriginatingCountry("UK");

        List<Message> messageList = new ArrayList<>();
        messageList.add(messageObject);
        messageList.add(messageObject1);
        String currencyFrom ="EUR";
        String currencyTo = "GBP";
        String originatingCountry = "UK";

        List<Message> messageListExpected = new ArrayList<>();
        messageListExpected.add(messageObject);
        messageListExpected.add(messageObject1);

        when(
                messageDAOMock.getMessageIn60SecondTimeWindow(currencyFrom,currencyTo,originatingCountry)
        ).thenReturn(
                messageList
        );
        List<Message> listMessage = messageDAOMock.getMessageIn60SecondTimeWindow(currencyFrom,currencyTo,originatingCountry);
        assertEquals(messageListExpected,listMessage);
    }

    @Test
    public void testGetMessages60SecondTimeWindowStatistics() {
        WindowStatistics windowStatistics = new WindowStatistics();
        windowStatistics.setTransactionCount(10);
        windowStatistics.setMaxAmountBuy(1000);
        windowStatistics.setAvgAmountBuy(1000);
        windowStatistics.setMaxAmountBuy(1000);
        windowStatistics.setMaxAmountSell(1000);
        windowStatistics.setAvgAmountSell(1000);
        windowStatistics.setSumAmountSell(1000);
        String currencyFrom ="EUR";
        String currencyTo = "GBP";
        String originatingCountry = "UK";
        when(
                messageDAOMock.getMessageIn60SecondTimeWindowStatistics(currencyFrom,currencyTo,originatingCountry)
        ).thenReturn(
                windowStatistics
        );
        WindowStatistics windowStatisticsReturned = messageDAOMock.getMessageIn60SecondTimeWindowStatistics(currencyFrom,currencyTo,originatingCountry);
        assertEquals(windowStatistics,windowStatisticsReturned);
    }
}