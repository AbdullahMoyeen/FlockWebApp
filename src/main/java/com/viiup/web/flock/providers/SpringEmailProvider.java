package com.viiup.web.flock.providers;

import com.viiup.web.flock.providers.interfaces.IEmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by AbdullahMoyeen on 2/3/16.
 */
@Service
public class SpringEmailProvider implements IEmailProvider {

    @Autowired
    private JavaMailSender mailSender;

    private static final String DEFAULT_FROM_ADDRESS = "Flock <viiup.utd.emse@gmail.com>";

    public void sendEmail(String[] toAddresses, String subject, String messageText) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(DEFAULT_FROM_ADDRESS);
            messageHelper.setTo(toAddresses);
            messageHelper.setSubject(subject);
            messageHelper.setText(getHtmlMessage(messageText), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getHtmlMessage(String messageText){

        StringBuilder htmlMessage = new StringBuilder();

        htmlMessage.append("<html>\n");
        htmlMessage.append("    <body>\n");
        htmlMessage.append("        <div style=\"background-color:#263238; color:white; width:100%; height:80px; padding:5px; text-align:center; font-size: large;\">\n");
        htmlMessage.append("            <h1>Flock</h1>\n");
        htmlMessage.append("        </div>\n");
        htmlMessage.append("        <div style=\"background-color:#0f9d58; width:100%; height:20px; padding:5px; float:top;\">\n");
        htmlMessage.append("          \n");
        htmlMessage.append("        </div>\n");
        htmlMessage.append("        <div style=\"width:100%; height: 100px; padding:5px; float:top;\">\n");
        htmlMessage.append("            <table style=\"width:100%; font-size: large; color: black; text-align: center;border-bottom: 2px solid black;\">\n");
        htmlMessage.append("                <tr>\n");
        htmlMessage.append("                    <td style=\"text-align: center;\">\n");
        htmlMessage.append(messageText);
        htmlMessage.append("                    </td>\n");
        htmlMessage.append("                </tr>\n");
        htmlMessage.append("                <tr>\n");
        htmlMessage.append("                    <td style=\"text-align: center;\">&nbsp;</td>\n");
        htmlMessage.append("                </tr>\n");
        htmlMessage.append("            </table>\n");
        htmlMessage.append("        </div>\n");
        htmlMessage.append("    </body>\n");
        htmlMessage.append("</html>");

        return htmlMessage.toString();
    }
}