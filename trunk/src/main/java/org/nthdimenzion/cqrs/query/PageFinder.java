package org.nthdimenzion.cqrs.query;

import com.google.common.collect.Lists;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.*;
import org.nthdimenzion.cqrs.query.annotations.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 24/4/13
 * Time: 10:22 PM
 */
@Finder
@Component
public class PageFinder implements IPageFinder {

    public static final String EXCEPTION_MESSAGE = "Cannot perform page because countQuery Result Map is not defined " +
            "Copy the below Result Map into any *Finder.xml file" +
            "" +
            "  <resultMap id=\"countQuery\" type=\"java.lang.Integer\">\n" +
            "        <result property=\"total\" column=\"total\"/>\n" +
            "    </resultMap>";
    private SqlSessionFactory sqlSessionFactory;

    private static final Object NOPARAMS = null;

    @Autowired
    public PageFinder(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public IPage findAll(String queryId, RowBounds rowBounds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List allRecords = sqlSession.selectList(queryId, NOPARAMS, rowBounds);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(queryId);

        String sql = mappedStatement.getBoundSql(NOPARAMS).getSql();
        String countQuery = createCountQuery(sql);
        SqlSource countQuerySqlSource = createCountQuerySqlSource(mappedStatement, countQuery, null);
        final String countQueryId = addMappedStatementForQueryIntoMyBatisConfiguration(queryId, configuration, countQuerySqlSource);
        Object result = sqlSession.selectOne(countQueryId);
        IPage page = new Page(allRecords, rowBounds, Integer.valueOf(result.toString()));
        return page;
    }

    public IPage find(String queryId, Map queryParams, RowBounds rowBounds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List allRecords = sqlSession.selectList(queryId, queryParams, rowBounds);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(queryId);
        String sql = mappedStatement.getBoundSql(queryParams).getSql();
        String countQuery = createCountQuery(sql);
        SqlSource countQuerySqlSource = createCountQuerySqlSource(mappedStatement, countQuery, queryParams);
        final String countQueryId = addMappedStatementForQueryIntoMyBatisConfiguration(queryId, configuration, countQuerySqlSource);
        Object result = sqlSession.selectOne(countQueryId, queryParams);
        IPage page = new Page(allRecords, rowBounds, Integer.valueOf(result.toString()));
        return page;
    }

    @Override
    public IPage find(String queryId, Object queryParams, RowBounds rowBounds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List allRecords = sqlSession.selectList(queryId, queryParams, rowBounds);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(queryId);
        String sql = mappedStatement.getBoundSql(queryParams).getSql();
        String countQuery = createCountQuery(sql);
        SqlSource countQuerySqlSource = createCountQuerySqlSource(mappedStatement, countQuery, queryParams);
        final String countQueryId = addMappedStatementForQueryIntoMyBatisConfiguration(queryId, configuration, countQuerySqlSource);
        Object result = sqlSession.selectOne(countQueryId, queryParams);
        IPage page = new Page(allRecords, rowBounds, Integer.valueOf(result.toString()));
        return page;
    }

    private String addMappedStatementForQueryIntoMyBatisConfiguration(String queryId, Configuration configuration, SqlSource countQuerySqlSource) {
        final String countQueryId = queryId.concat("_count");
        if (!configuration.hasResultMap("countQuery")) {
            throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
        }

        ResultMap countResultMap = configuration.getResultMap("countQuery");

        if (!configuration.hasStatement(countQueryId)) {
            MappedStatement countStatement = new MappedStatement.Builder(configuration, countQueryId,
                    countQuerySqlSource, SqlCommandType.SELECT).resultMaps(Lists.newArrayList(countResultMap)).build();
            configuration.addMappedStatement(countStatement);
        }
        return countQueryId;
    }

    private SqlSource createCountQuerySqlSource(MappedStatement mappedStatement, String countQuery, Object queryParams) {
        if (queryParams == null) {
            return new StaticSqlSource(mappedStatement.getConfiguration(), countQuery);
        } else {
            return new StaticSqlSource(mappedStatement.getConfiguration(), countQuery,
                    mappedStatement.getBoundSql(queryParams).getParameterMappings());
        }
    }


    String createCountQuery(final String sql) {
        final String capitalizedSql = sql.toUpperCase();
        int startIndex = capitalizedSql.indexOf("SELECT") + 6;
        StringBuilder countQueryBuilder = new StringBuilder(capitalizedSql);
        countQueryBuilder = countQueryBuilder.delete(startIndex, capitalizedSql.indexOf(" FROM"));
        String countQuery = countQueryBuilder.toString().replace("SELECT", "SELECT COUNT(*) as total ");
        return countQuery.trim();
    }

}
