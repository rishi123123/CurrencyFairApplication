package DAO;

import Model.LabelData;
import Model.Message;
import Model.WindowStatistics;

import java.util.Date;
import java.util.List;


public interface MessageDAO {
     boolean insertMessageIntoDB(Message message);

     List<Message> getAllMessages();

     List<Message> getMessagesBasedOnCurrency(String currency,String toOrFromString);

     List<Message> getMessageBasedOnTime(Date time, String operator);

     List<Message> getMessageBasedOnOriginatingCountry(String originationCountry);

     List<Message> getMessageBasedOnFilter(String currencyFrom, String currencyTo, String originatingCountry);

     List<Message> getMessageIn60SecondTimeWindow(String currencyFrom, String currencyTo, String originatingCountry);

     WindowStatistics getMessageIn60SecondTimeWindowStatistics(String currencyFrom, String currencyTo, String originatingCountry);

     List<LabelData> getCurrencyFromDataForGraph();

    List<LabelData> getCurrencyFromDataForLine();

    List<LabelData> getCurrencyFromDataForPie();
}
