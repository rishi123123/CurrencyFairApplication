package Service;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import DAO.MessageDAO;
import Model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    MessageDAO messageDAO;

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);
    private ReentrantLock concurrentLock = new ReentrantLock();
    private static long counter;

    @Override
    public boolean insertMessageUnit(Message message) {
        boolean response = messageDAO.insertMessageIntoDB(message);
        if (response) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Message> getAllMessages() {
        LOG.info("Fetch all messages");
        List<Message> messageList = messageDAO.getAllMessages();
        return messageList;
    }

    @Override
    public List<Message> getAllMessagesBasedOnCurrency(String currency, String toOrFromString) {
        LOG.info("Fetch all messages based on currency:" + currency);
        List<Message> messageList = messageDAO.getMessagesBasedOnCurrency(currency, toOrFromString);
        return messageList;
    }

    @Override
    public List<Message> getAllMessagesBasedOnTime(Date time, String operator) {
        List<Message> messageList = messageDAO.getMessageBasedOnTime(time, operator);
        return messageList;
    }

    @Override
    public List<Message> getAllMessagesBasedOnOriginatingCounty(String originationCountry) {
        LOG.info("Fetch messages based on OriginatingCountry" + originationCountry);
        List<Message> messageList = messageDAO.getMessageBasedOnOriginatingCountry(originationCountry);
        return messageList;
    }

    @Override
    public List<Message> getMessagesBasedOnFilter(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("Fetch messages with filter values currencyFrom:" + currencyFrom
                + " currencyTo:" + currencyTo
                + "originatingCountry" + originatingCountry);
        List<Message> messageList = messageDAO.getMessageBasedOnFilter(currencyFrom, currencyTo, originatingCountry);
        return messageList;
    }

    @Override
    public List<Message> getMessages60SecondTimeWindow(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("Fetch messages with filter values within 60 second time window currencyFrom:" + currencyFrom
                + " currencyTo:" + currencyTo
                + "originatingCountry" + originatingCountry);
        return messageDAO.getMessageIn60SecondTimeWindow(currencyFrom, currencyTo, originatingCountry);

    }

    @Override
    public WindowStatistics getMessages60SecondTimeWindowStatistics(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("Fetch messages with filter values within 60 second time window currencyFrom:" + currencyFrom
                + " currencyTo:" + currencyTo
                + "originatingCountry" + originatingCountry);
        return messageDAO.getMessageIn60SecondTimeWindowStatistics(currencyFrom, currencyTo, originatingCountry);

    }

    @Override
    public List<LabelData> getDataForGraph() {
        LOG.info("Fetch messages with filter values within 60 second time window currencyFrom:");
        return messageDAO.getCurrencyFromDataForGraph();
    }

    @Override
    public List<LabelData> getDataForLine() {
        LOG.info("Fetch messages with filter values within 60 second time window currencyFrom:");
        return messageDAO.getCurrencyFromDataForLine();
    }

    @Override
    public List<LabelData> getDataForPieChart() {
        LOG.info("Fetch messages with filter values within 60 second time window currencyFrom:");
        return messageDAO.getCurrencyFromDataForPie();
    }

}
