package org.nthdimenzion.ddd.domain.sharedkernel;

import org.apache.ibatis.type.*;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = DateTime.class)
@MappedJdbcTypes(value = {JdbcType.DATE,JdbcType.TIME,JdbcType.TIMESTAMP})
public class MyBatisJodaDateTimeType extends BaseTypeHandler<DateTime>{

    public MyBatisJodaDateTimeType() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new java.sql.Timestamp((parameter.toDate()).getTime()));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        java.sql.Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        if (sqlTimestamp != null) {
            return new DateTime(sqlTimestamp.getTime());
        }
        return null;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        java.sql.Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
        if (sqlTimestamp != null) {
            return new DateTime(sqlTimestamp.getTime());
        }
        return null;
    }
}
