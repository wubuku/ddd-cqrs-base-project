package org.nthdimenzion.presentation.infrastructure;

import com.google.common.collect.Maps;
import com.librarymanagement.presentation.queries.DateOfBirthCriteria;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.nthdimenzion.presentation.ICriteria;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/4/13
 * Time: 1:47 PM
 */


@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class TenantFilterInterceptor implements Interceptor {

    /**
     * mapped statement parameter index.
     */
    private static final int MAPPED_STATEMENT_INDEX = 0;
    /**
     * parameter index.
     */
    private static final int PARAMETER_INDEX = 1;

    private static final String field = "entityStatus";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];

        Object parameter = queryArgs[PARAMETER_INDEX];

        final BoundSql boundSql = ms.getBoundSql(parameter);

        List<ParameterMapping> parameterMappings =  boundSql.getParameterMappings();

        boolean hasFilterField = false;
        for(ParameterMapping parameterMapping : parameterMappings){
            if(parameterMapping.getProperty().equalsIgnoreCase(field)){
                hasFilterField = true;
                break;
            }
        }

        if(!hasFilterField){
            invocation.proceed();
        }

        String sql = boundSql.getSql().trim().toUpperCase();

        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, queryArgs);
        MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
        queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    //see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        String keyProperties = ms.getKeyProperty();
        builder.keyProperty(keyProperties);

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql, Object[] queryArgs) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Object parameterValue = queryArgs[PARAMETER_INDEX];

        Map mapParam = Maps.newHashMap();

        boolean isSingleParam = false;
        if (parameterValue instanceof Map) {
            mapParam = (Map) parameterValue;
        } else if (parameterValue instanceof ICriteria) {
            mapParam = BeanUtils.describe(parameterValue);
        } else {
            isSingleParam = true;
        }

        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (isSingleParam && !prop.equalsIgnoreCase(field)) {
                mapParam.put(prop, parameterValue);
            }
            if(prop.equalsIgnoreCase(field)){
                mapParam.put(field, 1);
            }
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        queryArgs[PARAMETER_INDEX] = mapParam;
        return newBoundSql;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
