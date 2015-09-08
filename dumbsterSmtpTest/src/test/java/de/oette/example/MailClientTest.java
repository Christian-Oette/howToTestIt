package de.oette.example;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class MailClientTest {

    private static final int PORT = 33000;

    @Test
    public void testSend() {
        SimpleSmtpServer server = SimpleSmtpServer.start(33000);
        MailClient mailClient = new MailClient(PORT);
        mailClient.sendMail();
        server.stop();

        assertTrue(server.getReceivedEmailSize() == 1);

        Iterator emailIter = server.getReceivedEmail();
        SmtpMessage email = (SmtpMessage)emailIter.next();
        assertTrue(email.getHeaderValue("Subject").equals("Testing Subject"));
        assertTrue(email.getBody().equals("Hello SMTP Server"));
    }
}
