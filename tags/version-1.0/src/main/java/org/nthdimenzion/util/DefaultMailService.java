package org.nthdimenzion.util;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

public class DefaultMailService implements IMailService<Map>{

    private final VelocityEngine velocityEngine;
    private final JavaMailSenderImpl javaMailSender;
    private final Map<String,String> mailSettings;

     private final Logger logger = LoggerFactory.getLogger(DefaultMailService.class);

    public DefaultMailService(JavaMailSenderImpl javaMailSender, VelocityEngine velocityEngine,Map<String,String> mailSettings) {
        this.javaMailSender = javaMailSender;
        this.velocityEngine = velocityEngine;
        this.mailSettings = mailSettings;
    }

    @Override
    public void sendMail(final Map model) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
         public void prepare(MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(mailSettings.get("RecipientEmailAddress"));
            message.setFrom(mailSettings.get("SenderEmailAddress"));
            message.setSubject(mailSettings.get("Subject"));
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, mailSettings.get("TemplatePath"), model);
            logger.debug(text);
            message.setText(text, true);
         }
      };
      this.javaMailSender.send(preparator);
    }


}
