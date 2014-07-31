package org.kyffa.general;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    private String name;
    private String email;
    private String message;

    public SendEmail(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public void sendMail() {
        System.out.println("Setting up Mail Properties....");
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        System.out.println("Getting mail Session....");
        Session session = Session.getDefaultInstance(properties, null);


        try {
            System.out.println("Creating message");
            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(this.email));
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sheldon.burks@gmail.com"));
            emailMessage.setSubject("New Bug Report from " + this.name);
            emailMessage.setText(this.message);

            Transport transport = session.getTransport("smtp");
            System.out.println("Connecting....");
            transport.connect("smtp.office365.com", "sheldon.burks", "s316245b?");
            System.out.println("Trying to send...");
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
            System.out.println("Message Sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
