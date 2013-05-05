package org.nthdimenzion.cqrs.query;

import org.apache.ibatis.session.RowBounds;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 25/4/13
 * Time: 10:37 AM
 */
public final class PagingUtil {

    public final static RowBounds TENRECORDSPERPAGE(int offset){
        return new RowBounds(offset,10);
    }

    public final static RowBounds TWENTYRECORDSPERPAGE(int offset){
        return new RowBounds(offset,20);
    }
}
