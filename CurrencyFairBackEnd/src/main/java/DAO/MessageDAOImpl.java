package DAO;

import Mapper.LabelRowMapper;
import Mapper.MessageRowMapper;
import Mapper.WindowStatisticsRowMapper;
import Model.LabelData;
import Model.Message;
import Model.WindowStatistics;
import Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean insertMessageIntoDB(Message message) {
        try {
            LOG.info("Inserting Message into the DB");
            String sql = " INSERT INTO `message` ( `userid`, `currencyFrom`, `currencyTo`, `amount_sell`, `amount_buy`, `rate`, `time_placed`, `originating_country`)"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    new Object[]{
                            message.getUserId(),
                            message.getCurrencyFrom(),
                            message.getCurrencyTo(),
                            message.getAmoutSell(),
                            message.getAmountBuy(),
                            message.getRate(),
                            message.getTimePlaced(),
                            message.getOriginatingCountry()
                    }
            );
        } catch (DataAccessException e) {
            LOG.error("Data Access Exception while inserting data" + e.getMessage());
            return false;
        } catch (Exception e) {
            LOG.error("Exception occured while inserting the data" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Message> getAllMessages() {
        try {
            LOG.info("DAO:Fetch all messages from DB");
            return jdbcTemplate.query("select * from message", new MessageRowMapper());
        } catch (Exception e) {
            LOG.error("Data Access Exception while inserting data" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Message> getMessagesBasedOnCurrency(String currency, String toOrFromString) {
        LOG.info("DAO:Fetch all messages from DB based on currency");
        String sql = null;
        if (toOrFromString.equals("from")) {
            sql = "select * from message where currencyfrom = ?";
        } else if (toOrFromString.equals("to")) {
            sql = "select * from message where currencyto = ?";
        }
        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{currency},
                    new MessageRowMapper()
            );
        } catch (Exception e) {
            LOG.error("Data Access Exception while fetching data based on currency" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Message> getMessageBasedOnTime(Date time, String operator) {
        LOG.info("DAO:Fetch all messages from DB based on time");
        String sql = null;
        if (operator.equals("Less")) {
            sql = "select * from message where time_placed < ?";
        } else {
            sql = "select * from message where time_placed > ?";
        }
        try {
            return jdbcTemplate.query(
                    sql,
                    new Object[]{time},
                    new MessageRowMapper()
            );
        } catch (DataAccessException e) {
            LOG.error("Data Access Exception while fetching data based on time" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Message> getMessageBasedOnOriginatingCountry(String originationCountry) {
        LOG.info("DAO:Fetch all messages from DB based on country of origin");
        try {
            return jdbcTemplate.query(
                    "select * from message where originating_country = ? ",
                    new Object[]{originationCountry},
                    new MessageRowMapper()
            );
        } catch (Exception e) {
            LOG.error("Data Access Exception while fetching data filter originating country" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Message> getMessageBasedOnFilter(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("DAO:Fetch all messages from DB based on filters in the form");
        try {
            String baseQuery = "SELECT * from message where userID is not null";
            baseQuery = queryBuilder(baseQuery,currencyFrom,currencyTo,originatingCountry);
            MapSqlParameterSource params = paramSourceBuilder(currencyFrom,currencyTo,originatingCountry);
            List<Message> messageList =
                    namedParameterJdbcTemplate.query(
                            baseQuery,
                            params,
                            new MessageRowMapper()
                    );
            return messageList;
        } catch (Exception e) {
            LOG.error("Data Access Exception while fetching data filter method" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Message> getMessageIn60SecondTimeWindow(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("DAO:Fetch all messages from DB for 60 second time window");
        try {
            String baseQuery = "SELECT * FROM MESSAGE " +
                    "WHERE TIMESTAMPDIFF(SECOND,TIME_PLACED,NOW()) < 60 ";
            baseQuery = queryBuilder(baseQuery,currencyFrom,currencyTo,originatingCountry);
            MapSqlParameterSource params = paramSourceBuilder(currencyFrom,currencyTo,originatingCountry);
            List<Message> messageList =
                    namedParameterJdbcTemplate.query(
                            baseQuery,
                            params,
                            new MessageRowMapper()
                    );
            return messageList;
        } catch (DataAccessException e) {
            LOG.info("Data Access Exception while inserting data for window data fetch" + e.getMessage());
            return null;
        }
    }

    @Override
    public WindowStatistics getMessageIn60SecondTimeWindowStatistics(String currencyFrom, String currencyTo, String originatingCountry) {
        LOG.info("DAO:Fetch all messages from DB for 60 second time window statistics");
        try {
            String baseQuery = "SELECT COUNT(ID) as TransactionCount,SUM(amount_sell) as sum_amount_sell," +
                    " AVG(amount_sell) as avg_amount_sell, MAX(amount_sell) as max_amount_sell ,SUM(amount_buy) as sum_amount_buy, " +
                    "AVG(amount_buy) as avg_amount_buy, MAX(amount_buy) as max_amount_buy " +
                    "FROM MESSAGE " +
                    "WHERE TIMESTAMPDIFF(SECOND,TIME_PLACED,NOW()) < 60 ";
            baseQuery = queryBuilder(baseQuery,currencyFrom,currencyTo,originatingCountry);
            MapSqlParameterSource params = paramSourceBuilder(currencyFrom,currencyTo,originatingCountry);
            return namedParameterJdbcTemplate.queryForObject(baseQuery, params, new WindowStatisticsRowMapper());
        } catch (DataAccessException e) {
            LOG.info("Data Access Exception while inserting data for window data fetch statistics" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<LabelData> getCurrencyFromDataForGraph() {
        LOG.info("DAO:Fetch all messages from DB for bar graph");
        try {
            String baseQuery = "SELECT CURRENCYFROM as label,COUNT(ID) as value FROM MESSAGE" +
                    " GROUP BY CURRENCYFROM";
            return jdbcTemplate.query(
                    baseQuery,
                    new Object[]{},
                    new LabelRowMapper());
        } catch (Exception e) {
            LOG.info("Data Access Exception while fetching data for currency bar graph" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<LabelData> getCurrencyFromDataForLine() {
        LOG.info("DAO:Fetch all messages from DB for LineChart");
        try {
            String baseQuery = "SELECT EXTRACT(YEAR FROM TIME_PLACED) as label, count(ID) as value " +
                    "FROM MESSAGE " +
                    "GROUP BY label " +
                    "ORDER BY label";
            return jdbcTemplate.query(
                    baseQuery,
                    new Object[]{},
                    new LabelRowMapper());
        } catch (Exception e) {
            LOG.info("Data Access Exception while inserting data for Pie Chart" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<LabelData> getCurrencyFromDataForPie() {
        LOG.info("DAO:Fetch all messages from DB for PieChart");
        try {
            String baseQuery = "SELECT ORIGINATING_COUNTRY as label,COUNT(ID) as value FROM MESSAGE" +
                    " GROUP BY ORIGINATING_COUNTRY";
            return jdbcTemplate.query(
                    baseQuery,
                    new Object[]{},
                    new LabelRowMapper());
        } catch (Exception e) {
            LOG.info("Data Access Exception while inserting data for Pie Chart" + e.getMessage());
            return null;
        }
    }

    public String queryBuilder(String baseQuery,String currencyFrom, String currencyTo, String originatingCountry) {
        if (!StringUtils.isEmpty(currencyFrom)) {
            baseQuery += " and currencyFrom = :currencyFrom ";
        }
        if (!StringUtils.isEmpty(currencyTo)) {
            baseQuery += " and currencyTo = :currencyTo";
        }
        if (!StringUtils.isEmpty(originatingCountry)) {
            baseQuery += " and originating_country = :originatingCountry";
        }
        return baseQuery;
    }

    public MapSqlParameterSource paramSourceBuilder(String currencyFrom, String currencyTo, String originatingCountry) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (!StringUtils.isEmpty(currencyFrom)) {
            params.addValue("currencyFrom", currencyFrom);
        }
        if (!StringUtils.isEmpty(currencyTo)) {
            params.addValue("currencyTo", currencyTo);
        }
        if (!StringUtils.isEmpty(originatingCountry)) {
            params.addValue("originatingCountry", originatingCountry);
        }
        return params;
    }
}
