package org.nthdimenzion.ddd.domain.sharedkernel;

import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = DateTime.class)
@MappedJdbcTypes(value = {JdbcType.DATE,JdbcType.TIME,JdbcType.TIMESTAMP})
public class MyBatisJodaDateTimeType extends DateTypeHandler {

    public MyBatisJodaDateTimeType() {
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        java.sql.Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        if (sqlTimestamp != null) {
            return new DateTime(sqlTimestamp.getTime());
        }
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        java.sql.Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
        if (sqlTimestamp != null) {
            return new DateTime(sqlTimestamp.getTime());
        }
        return null;
    }
}
