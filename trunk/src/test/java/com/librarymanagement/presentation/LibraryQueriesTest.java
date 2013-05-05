package com.librarymanagement.presentation;

import com.google.common.collect.Lists;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.presentation.infrastructure.MyBatisQueryResultColumnLabelsTestPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/4/13
 * Time: 12:58 PM
 */
@SuppressWarnings("ALL")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testContext.xml","classpath:/queryContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class LibraryQueriesTest {

    @Autowired
    protected ILibraryFinder libraryFinder;


    @Autowired
    private MyBatisQueryResultColumnLabelsTestPlugin myBatisQueryResultColumnLabelsTestPlugin;

    @Test
    public void testData(){
        myBatisQueryResultColumnLabelsTestPlugin.setExpectedColumnNames(Lists.newArrayList("firstName","lastName","middleName",
                "dateOfBirth","id"));
        Object result = libraryFinder.upcomingBirthDays(DateTime.now().toDate());
        Assert.assertNotNull(result);
    }
}
