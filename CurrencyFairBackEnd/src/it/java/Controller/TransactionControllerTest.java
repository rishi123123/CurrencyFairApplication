package Controller;

import Model.LabelData;
import Model.Message;
import Model.MessageCreateRequest;
import Model.WindowStatistics;
import Service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import Exception.MessageDateFormatException;
import static org.junit.Assert.*;



@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

    @Mock
    TransactionService transactionService;

    @Test
    public void testConsumeMessageSuccess() throws MessageDateFormatException {
        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(new Date());
        messageObject.setOriginatingCountry("FR");

        MessageCreateRequest mcr= new MessageCreateRequest();
        mcr.setUserId(134256);
        mcr.setCurrencyFrom("AUD");
        mcr.setCurrencyTo("INR");
        mcr.setAmountBuy(1000);
        mcr.setAmoutSell(1000);
        mcr.setRate(0.01);
        mcr.setTimePlaced("15-JAN-18 12:14:00");
        mcr.setOriginatingCountry("FR");

        Message messageFormed = Message.toMessage(mcr);
        when(transactionService.insertMessageUnit(messageFormed))
                .thenReturn(true);
        boolean success = transactionService.insertMessageUnit(messageObject);
        assertTrue(success);
    }

    @Test(expected = MessageDateFormatException.class)
    public void testConsumeMessageFailureDueToMessageFormat() throws MessageDateFormatException {
        Message messageObject = new Message();
        messageObject.setUserId(134256);
        messageObject.setCurrencyFrom("AUD");
        messageObject.setCurrencyTo("INR");
        messageObject.setAmountBuy(1000);
        messageObject.setAmoutSell(1000);
        messageObject.setRate(0.01);
        messageObject.setTimePlaced(new Date());
        messageObject.setOriginatingCountry("FR");

        MessageCreateRequest mcr= new MessageCreateRequest();
        mcr.setUserId(134256);
        mcr.setCurrencyFrom("AUD");
        mcr.setCurrencyTo("INR");
        mcr.setAmountBuy(1000);
        mcr.setAmoutSell(1000);
        mcr.setRate(0.01);
        mcr.setTimePlaced("15-10-18 12:14:00");
        mcr.setOriginatingCountry("FR");

        Message messageFormed = Message.toMessage(mcr);
        when(transactionService.insertMessageUnit(messageFormed))
                .thenReturn(false);
        boolean success = transactionService.insertMessageUnit(messageObject);
        assertFalse(success);
    }

    @Test
    public void testGetMessagesBasedOnFilter() {
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

        String currencyFrom = "EUR";
        String currencyTo = "USD";
        String originatingCountry= "UK";
        when(
                transactionService.getMessagesBasedOnFilter
                        (
                            currencyFrom,
                            currencyTo,
                            originatingCountry
                        )
        )
                .thenReturn(messageList);
        List<Message> messageListReturned = transactionService.getMessagesBasedOnFilter(currencyFrom,currencyTo,originatingCountry);
        assertEquals(messageList,messageListReturned);
    }

    @Test
    public void testGetMessagesInAMinuteWindow() {
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

        String currencyFrom = "EUR";
        String currencyTo = "USD";
        String originatingCountry= "UK";
        when(
                transactionService.getMessages60SecondTimeWindow
                        (
                                currencyFrom,
                                currencyTo,
                                originatingCountry
                        )
        )
                .thenReturn(messageList);
        List<Message> messageListReturned =
                transactionService.getMessages60SecondTimeWindow
                        (
                                currencyFrom,
                                currencyTo,
                                originatingCountry
                        );
        assertEquals(messageList,messageListReturned);
    }

    @Test
    public void testGetMessagesInAMinuteWindowStatistics() {
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
                transactionService.getMessages60SecondTimeWindowStatistics
                        (
                                currencyFrom,
                                currencyTo,
                                originatingCountry
                        )
        )
                .thenReturn(windowStatistics);
        WindowStatistics windowStatisticsReturned =
                transactionService.getMessages60SecondTimeWindowStatistics
                        (
                                currencyFrom,
                                currencyTo,
                                originatingCountry
                        );
        assertEquals(windowStatistics,windowStatisticsReturned);
    }

    @Test
    public void testGetDataForLine() {
        LabelData lb = new LabelData();
        lb.setValue("4");
        lb.setLabel("FR");
        LabelData lb1 = new LabelData();
        lb1.setValue("2");
        lb1.setLabel("UK");
        List<LabelData> labelList = new ArrayList<LabelData>();
        labelList.add(lb);
        labelList.add(lb1);

        LabelData lbExpected = new LabelData();
        lbExpected.setValue("1");
        lbExpected.setLabel("IN");
        LabelData lb1E = new LabelData();
        lb1E.setValue("2");
        lb1E.setLabel("label2");
        List<LabelData> labelListExpected = new ArrayList<LabelData>();
        labelListExpected.add(lb);
        labelListExpected.add(lb1);

        when(
                transactionService.getDataForLine()
        )
                .thenReturn(labelListExpected);
        List<LabelData> labelListReturned =
                transactionService.getDataForLine();
        assertEquals(labelListExpected,labelListReturned);
    }

    @Test
    public void testGetDataForGraph() {
        LabelData lb = new LabelData();
        lb.setValue("4");
        lb.setLabel("FR");
        LabelData lb1 = new LabelData();
        lb1.setValue("2");
        lb1.setLabel("UK");
        List<LabelData> labelList = new ArrayList<LabelData>();
        labelList.add(lb);
        labelList.add(lb1);

        LabelData lbExpected = new LabelData();
        lbExpected.setValue("1");
        lbExpected.setLabel("IN");
        LabelData lb1E = new LabelData();
        lb1E.setValue("2");
        lb1E.setLabel("label2");
        List<LabelData> labelListExpected = new ArrayList<LabelData>();
        labelListExpected.add(lb);
        labelListExpected.add(lb1);

        when(
                transactionService.getDataForGraph()
        )
                .thenReturn(labelListExpected);
        List<LabelData> labelListReturned =
                transactionService.getDataForGraph();
        assertEquals(labelListExpected,labelListReturned);
    }

    @Test
    public void testGetDataForPieChart() {
        LabelData lb = new LabelData();
        lb.setValue("4");
        lb.setLabel("FR");
        LabelData lb1 = new LabelData();
        lb1.setValue("2");
        lb1.setLabel("UK");
        List<LabelData> labelList = new ArrayList<LabelData>();
        labelList.add(lb);
        labelList.add(lb1);

        LabelData lbExpected = new LabelData();
        lbExpected.setValue("1");
        lbExpected.setLabel("IN");
        LabelData lb1E = new LabelData();
        lb1E.setValue("2");
        lb1E.setLabel("label2");
        List<LabelData> labelListExpected = new ArrayList<LabelData>();
        labelListExpected.add(lb);
        labelListExpected.add(lb1);

        when(
                transactionService.getDataForPieChart()
        )
                .thenReturn(labelListExpected);
        List<LabelData> labelListReturned =
                transactionService.getDataForPieChart();
        assertEquals(labelListExpected,labelListReturned);
    }

}