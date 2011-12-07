package integration.org.nthdimenzion.util;

import com.google.common.collect.ImmutableMap;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.util.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/testContext.xml", "classpath:/integrationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MailSenderTest {

    @Autowired
    @Qualifier("testMailService")
    private IMailService<Map> defaultMailService;

    private static GreenMail greenMail = null;

    @BeforeClass
    public static void setup() {
        greenMail = new GreenMail(); //uses test ports by default
        greenMail.start();
    }

    @AfterClass
    public static void tearDown() {
        greenMail.stop();
    }

    @Test
    public void testMailSending() throws Exception {
        defaultMailService.sendMail(ImmutableMap.of("userName", "Sudarshan"));
        Message[] messages = greenMail.getReceivedMessages();

        assertTrue(greenMail.waitForIncomingEmail(50000, 1));
        assertNotNull(GreenMailUtil.getBody(messages[0]));
        assertEquals("Default Test Mail", messages[0].getSubject());
        assertTrue(GreenMailUtil.getBody(messages[0]).contains("Sudarshan"));
    }
}
