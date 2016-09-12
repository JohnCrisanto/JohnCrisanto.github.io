package teamc;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by John on 9/4/2016.
 */
public class Email {

    public static void sendEmail(Defect defect) {

        //Establish properties for sending email
    
        Properties properties = System.getProperties();
        //Create authentication object with username and password for email above
 
        //Create a session using the properties established
        Session session = Session.getDefaultInstance(properties);

        //Create message and send message to assignee
        Message message = new MimeMessage(session);

        try {
            message.setSubject("Status Information for Defect " + defect.getDefectName() + ": " + defect.getSummary().toUpperCase());
            message.setText("Your ticket has been successfully created or updated.\n" + "\nDefect Ticket Information: \n"
                    + "\nApplication name: " + defect.getapplication() + "\n"
                    + "Assignee: " + defect.getAssignee() + "\n"
                    + "Defect Name: " + defect.getDefectName() + "\n"
                    + "Description: " + defect.getDescription() + "\n"
                    + "Priority: " + defect.getPriority() + "\n"
                    + "Status: " + defect.getStatus() + "\n"
                    + "Summary: " + defect.getSummary() + "\n");

            message.setFrom(new InternetAddress("johncrisantotest@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(defect.getAssignee()));

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}