package org.nthdimenzion.presentation.infrastructure.org.nthdimenzion.presentation.infrastructure.multitenant;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import com.google.common.base.Function;
import org.junit.Test;
import org.nthdimenzion.presentation.infrastructure.multitenant.UrlBasedTenantIdExtractor;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/5/13
 * Time: 11:40 AM
 */
@ActiveProfiles({"standalone"})
public class TestUrlBasedTenantIdExtractor {

    @Test
    public void givenUrlHost_WhenUrlStartWithWWWPrefix_ExtractTenantId(){
        Function<String,String> urlBasedExtractor = new UrlBasedTenantIdExtractor();
        assertThat("test2",equalTo(urlBasedExtractor.apply("www.test2.localhost:8080")));
    }

    @Test
    public void givenUrlHost_WhenUrlDoesNotStartWithWWWPrefix_ExtractTenantId(){
        Function<String,String> urlBasedExtractor = new UrlBasedTenantIdExtractor();
        assertThat("test2",is(equalTo(urlBasedExtractor.apply("test2.localhost:8080"))));
    }

}
