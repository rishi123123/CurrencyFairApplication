package DAO;

import Mapper.MessageRowMapper;
import Model.LabelData;
import Model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDAOImplTest {

    @Mock
    MessageDAOImpl messageDAOMock;

    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    MessageRowMapper messageRowMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testParamBuilder() {
        String currencyFrom = "USD";
        String currencyTo = "EUR";
        String originatingCountry = "UK";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("currencyFrom", currencyFrom);
        params.addValue("currencyTo", currencyTo);
        params.addValue("originatingCountry", originatingCountry);
        when(messageDAOMock.paramSourceBuilder(currencyFrom,currencyTo,originatingCountry))
                .thenReturn(params);
        MapSqlParameterSource returnedParamMap =
                messageDAOMock.paramSourceBuilder(currencyFrom,currencyTo,originatingCountry);
        assertEquals(params,returnedParamMap);
    }

    @Test
    public void testQueryBuilder() {
        String baseQuery = "SELECT * FROM MESSAGE " +
                "WHERE TIMESTAMPDIFF(SECOND,TIME_PLACED,NOW()) < 60 ";
        String formedQuery = "SELECT * FROM MESSAGE " +
                "WHERE TIMESTAMPDIFF(SECOND,TIME_PLACED,NOW()) < 60 "+
                " and currencyFrom = :currencyFrom " +
                " and currencyTo = :currencyTo " +
                " and originating_country = :originatingCountry";
        String currencyFrom = "USD";
        String currencyTo = "EUR";
        String originatingCountry = "UK";
        when(messageDAOMock.queryBuilder(baseQuery,currencyFrom,currencyTo,originatingCountry))
                .thenReturn(formedQuery);
        String returnedQuery =
                messageDAOMock.queryBuilder(baseQuery,currencyFrom,currencyTo,originatingCountry);
        assertEquals(formedQuery,returnedQuery);
    }

    @Test
    public void testgetDataForLine() {
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
        List<LabelData> labelListE = new ArrayList<LabelData>();
        labelListE.add(lb);
        labelListE.add(lb1);
        when(messageDAOMock.getCurrencyFromDataForPie())
                .thenReturn(labelList);
        List<LabelData> resultList = messageDAOMock.getCurrencyFromDataForPie();
        assertEquals(resultList, labelListE);
    }

    @Test
    public void testgetCurrencyFromDataForGraph() {
        LabelData lb = new LabelData();
        lb.setValue("6");
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
        List<LabelData> labelListE = new ArrayList<LabelData>();
        labelListE.add(lb);
        labelListE.add(lb1);
        when(messageDAOMock.getCurrencyFromDataForGraph())
                .thenReturn(labelList);
        List<LabelData> resultList = messageDAOMock.getCurrencyFromDataForGraph();
        assertEquals(resultList, labelListE);
    }

    @Test
    public void testgetCurrencyFromDataForPieChart() {
        LabelData lb = new LabelData();
        lb.setValue("5");
        lb.setLabel("FR");
        LabelData lb1 = new LabelData();
        lb1.setValue("2");
        lb1.setLabel("UK");
        List<LabelData> labelList = new ArrayList<LabelData>();
        labelList.add(lb);
        labelList.add(lb1);

        LabelData lbExpected = new LabelData();
        lbExpected.setValue("5");
        lbExpected.setLabel("IN");
        LabelData lb1E = new LabelData();
        lb1E.setValue("2");
        lb1E.setLabel("label2");
        List<LabelData> labelListE = new ArrayList<LabelData>();
        labelListE.add(lb);
        labelListE.add(lb1);
        when(messageDAOMock.getCurrencyFromDataForPie())
                .thenReturn(labelList);
        List<LabelData> resultList = messageDAOMock.getCurrencyFromDataForPie();
        assertEquals(resultList, labelListE);
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

        List<Message> messageListExpected = new ArrayList<>();
        messageListExpected.add(messageObject);
        messageListExpected.add(messageObject1);

        String baseQuery = "SELECT * from message where userID is not null";
        MapSqlParameterSource params = new MapSqlParameterSource();
        when(namedParameterJdbcTemplate.query(baseQuery,params,messageRowMapper))
                .thenReturn(messageList);
        List<Message> messageListReturned = namedParameterJdbcTemplate.query(baseQuery,params,messageRowMapper);
        assertEquals(messageListExpected,messageListReturned);
    }

    @Test
    public void testGetMessageIn60SecondTimeWindowStatistics() {
        String baseQuery = "SELECT COUNT(ID) as TransactionCount,SUM(amount_sell) as sum_amount_sell," +
                " AVG(amount_sell) as avg_amount_sell, MAX(amount_sell) as max_amount_sell ,SUM(amount_buy) as sum_amount_buy, " +
                "AVG(amount_buy) as avg_amount_buy, MAX(amount_buy) as max_amount_buy " +
                "FROM MESSAGE " +
                "WHERE TIMESTAMPDIFF(SECOND,TIME_PLACED,NOW()) < 60 ";
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

        List<Message> messageListExpected = new ArrayList<>();
        messageListExpected.add(messageObject);
        messageListExpected.add(messageObject1);
        MapSqlParameterSource params = new MapSqlParameterSource();
        when(namedParameterJdbcTemplate.query(baseQuery,params,messageRowMapper))
                .thenReturn(messageList);
        List<Message> messageListReturned = namedParameterJdbcTemplate.query(baseQuery,params,messageRowMapper);
        assertEquals(messageListExpected,messageListReturned);
    }
}