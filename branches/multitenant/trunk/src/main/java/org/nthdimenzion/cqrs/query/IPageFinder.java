package org.nthdimenzion.cqrs.query;

import com.google.common.base.Preconditions;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 24/4/13
 * Time: 10:35 PM
 */
public interface IPageFinder {

    public IPage findAll(String queryId,RowBounds rowBounds);

    public IPage find(String queryId,Map queryParams,RowBounds rowBounds);

    public IPage find(String queryId,Object queryParams,RowBounds rowBounds);
}
