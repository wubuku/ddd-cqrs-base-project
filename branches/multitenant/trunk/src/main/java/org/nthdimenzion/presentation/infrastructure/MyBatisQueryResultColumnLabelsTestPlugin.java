package org.nthdimenzion.presentation.infrastructure;

import com.google.common.collect.Lists;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.junit.Assert;
import org.nthdimenzion.object.utils.UtilValidator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/4/13
 * Time: 1:47 PM
 */
@Intercepts({@Signature(
        type= ResultSetHandler.class,
        method = "handleResultSets",
        args = {Statement.class})})
public class MyBatisQueryResultColumnLabelsTestPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if(UtilValidator.isEmpty(expectedColumnNames)){
            return invocation.proceed();
        }
        Object[] args = invocation.getArgs();
        Statement statement = (Statement) args[0];
        ResultSet rs = statement.getResultSet();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<String> columnNames = Lists.newArrayList();
        for (int i = 1; i <= columnCount; i++) {
            String columnLabel = rsmd.getColumnLabel(i);
            columnNames.add(columnLabel);
        }
        Assert.assertEquals(columnNames, expectedColumnNames);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    List<String> expectedColumnNames = Lists.newArrayList();

    public void setExpectedColumnNames(List<String> expectedColumnNames) {
        this.expectedColumnNames = expectedColumnNames;
    }
}
