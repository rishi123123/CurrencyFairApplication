package Mapper;

import Model.Message;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {


    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setUserId(resultSet.getLong("userid"));
        message.setAmoutSell(resultSet.getDouble("amount_sell"));
        message.setAmountBuy(resultSet.getDouble("amount_buy"));
        message.setCurrencyFrom(resultSet.getString("currencyfrom"));
        message.setCurrencyTo(resultSet.getString("currencyto"));
        message.setRate(resultSet.getDouble("rate"));
        message.setOriginatingCountry(resultSet.getString("originating_country"));
        message.setTimePlaced(resultSet.getDate("time_placed"));
        return message;
    }
}
