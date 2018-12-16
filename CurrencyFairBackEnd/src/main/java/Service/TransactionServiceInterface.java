package Service;

import java.util.Date;
import java.util.List;

import Model.*;

public interface TransactionServiceInterface {

    boolean insertMessageUnit(Message message);

    List<Message> getAllMessages();

    List<Message> getAllMessagesBasedOnCurrency(String currency, String toOrFromString);

    List<Message> getAllMessagesBasedOnTime(Date time, String operator);

    List<Message> getAllMessagesBasedOnOriginatingCounty(String originationCountry);

    List<Message> getMessagesBasedOnFilter(String currencyFrom, String currencyTo, String originatingCountry);

    List<Message> getMessages60SecondTimeWindow(String currencyFrom, String currencyTo, String originatingCountry);

    WindowStatistics getMessages60SecondTimeWindowStatistics(String currencyFrom, String currencyTo, String originatingCountry);

    List<LabelData> getDataForGraph();

    List<LabelData> getDataForLine();

    List<LabelData> getDataForPieChart();
}
