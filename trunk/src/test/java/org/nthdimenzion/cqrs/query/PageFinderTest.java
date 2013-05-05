package org.nthdimenzion.cqrs.query;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import junit.framework.TestSuite;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 25/4/13
 * Time: 8:14 AM
 */
public class PageFinderTest {

    @Test
    public void testPageNumberIsCalculatedGivenRowBounds(){
        Page page = new Page(Lists.newArrayList(),new RowBounds(20,5),100);
        Assert.assertEquals(5,page.getNumber());

    }


    @Test
    public void testPageSizeAndTotalNumberOfPagesIsCalculatedGivenRowBounds(){
        Page page = new Page(Lists.newArrayList(),PagingUtil.TWENTYRECORDSPERPAGE(40),100);
        Assert.assertEquals(3,page.getNumber());
        Assert.assertEquals(5,page.getTotalPages());

        page = new Page(Lists.newArrayList(),PagingUtil.TWENTYRECORDSPERPAGE(20),101);
        Assert.assertEquals(2,page.getNumber());
        Assert.assertEquals(6,page.getTotalPages());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRowBoundsAreAlwaysDivisible(){
        Page page = new Page(Lists.newArrayList(),new RowBounds(20,6),100);
        Assert.assertNotNull(page);
    }


    @Test
    public void testCountQueryCreation(){
        PageFinder pageFinder = new PageFinder(null);
        String actualResult = pageFinder.createCountQuery("SELECT b.uid as uid,\n" +
                "             b.id as id,\n" +
                "             b.name as name,\n" +
                "             b.isbn as isbn,\n" +
                "             b.authors as authors,\n" +
                "             bl.from_date as issueDate,\n" +
                "            AMOUNT as amount,\n" +
                "            CURRENCY_CODE as currencyCode\n" +
                "      FROM BOOK as b,BOOK_LENDING as bl\n" +
                "      where b.ENTITY_STATUS = 0\n" +
                "      and bl.member = #{memberId}\n" +
                "      and bl.book = b.id\n" +
                "      and bl.thru_date is null\n" +
                "      order by bl.id desc\n");

        String expectedResult = "SELECT COUNT(*) as total  FROM BOOK AS B,BOOK_LENDING AS BL\n" +
                "      WHERE B.ENTITY_STATUS = 0\n" +
                "      AND BL.MEMBER = #{MEMBERID}\n" +
                "      AND BL.BOOK = B.ID\n" +
                "      AND BL.THRU_DATE IS NULL\n" +
                "      ORDER BY BL.ID DESC";

        Assert.assertEquals(expectedResult,actualResult);
    }


}
