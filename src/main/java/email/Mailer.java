package email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mailer {
    public static void sendEmail(String host, String port, String from, String to, String subject, String message ){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
//        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(props);
        try{
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(from));
            email.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            email.setSubject(subject);
            email.setContent(message,"text/html");

            Transport.send(email);
        }catch(MessagingException e){
            System.out.println(e);
        }
    }
}
