package org.nthdimenzion.ddd.domain.sharedkernel;

import org.apache.ibatis.type.BigDecimalTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.jasypt.util.numeric.BasicDecimalNumberEncryptor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = BigDecimal.class )
@MappedJdbcTypes(value = {JdbcType.DECIMAL,JdbcType.NUMERIC,JdbcType.REAL,JdbcType.NULL})
public class EncryptedBigDecimalType extends BigDecimalTypeHandler {

    BasicDecimalNumberEncryptor decimalEncryptor = new BasicDecimalNumberEncryptor();

    public EncryptedBigDecimalType() {
        decimalEncryptor.setPassword("Nthdimenzion");
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        BigDecimal encryptedValue = rs.getBigDecimal(columnName);
        BigDecimal value = null;
        if (encryptedValue != null && "AMOUNT".equals(columnName))
            value = decimalEncryptor.decrypt(encryptedValue);
        return value;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        BigDecimal encryptedValue = cs.getBigDecimal(columnIndex);
        BigDecimal value = null;
        if (encryptedValue != null)
            value = decimalEncryptor.decrypt(encryptedValue);
        return value;
    }
}
