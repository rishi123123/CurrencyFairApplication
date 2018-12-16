package Mapper;

import Model.LabelData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelRowMapper implements RowMapper<LabelData> {

    @Override
    public LabelData mapRow(ResultSet resultSet, int i) throws SQLException {
        LabelData labelData = new LabelData();
        labelData.setLabel(resultSet.getString("label"));
        labelData.setValue(resultSet.getString("value"));
        return labelData;
    }
}
