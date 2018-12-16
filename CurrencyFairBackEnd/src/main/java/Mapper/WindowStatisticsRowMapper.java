package Mapper;

import Model.WindowStatistics;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WindowStatisticsRowMapper implements RowMapper<WindowStatistics> {

    @Override
    public WindowStatistics mapRow(ResultSet resultSet, int i) throws SQLException {
        WindowStatistics windowStatistics = new WindowStatistics();
        windowStatistics.setTransactionCount(resultSet.getLong("TransactionCount"));
        windowStatistics.setSumAmountSell(resultSet.getDouble("sum_amount_sell"));
        windowStatistics.setAvgAmountSell(resultSet.getDouble("avg_amount_sell"));
        windowStatistics.setMaxAmountSell(resultSet.getDouble("max_amount_sell"));
        windowStatistics.setSumAmountBuy(resultSet.getDouble("sum_amount_buy"));
        windowStatistics.setAvgAmountBuy(resultSet.getDouble("avg_amount_buy"));
        windowStatistics.setMaxAmountBuy(resultSet.getDouble("max_amount_buy"));
        return windowStatistics;
    }
}
