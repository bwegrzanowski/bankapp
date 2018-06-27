package sda.model;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail
{
    public static void send(String to, String sub,
                            final String user,final String pass, String token)
    {
        //create an instance of Properties Class
        Properties props = new Properties();

     /* Specifies the IP address of your default mail server
     	   for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host.
           As shown here in the code.
           Change accordingly, if your email id is not a gmail id
        */
        props.put("mail.smtp.host", "smtp.gmail.com");
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

     /* Pass Properties object(props) and Authenticator object
           for authentication to Session instance
        */

        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user,pass);
            }
        });

        try
        {

 	/* Create an instance of MimeMessage,
 	      it accept MIME types and headers
 	   */
            String mail = "<p>Hello!</p>\n" +
                    "<p>You are receiving this email because you have registered to our website.</p>\n" +
                    "<p>Your bankApp account has been created, please click <span style=\"font-size: 15pt;\">" +
                    "<a href=\"http://localhost:8080/activateUser?token=" + token + "\">here</a></span> to activate it.</p>\n" +
                    "<p>If you have not registered to our website, simply delete this mail.<br /><br /><br /></p>\n" +
                    "<p><span style=\"font-size: 8pt;\">This message comes from an unmonitored mailbox. Please do not reply to this message.</span></p>";
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
//            setting message as html
            message.setContent(mail,"text/html");
//           sending message as usual text  ->   message.setText(msg + mail);

            /* Transport class is used to deliver the message to the recipients */

            Transport.send(message);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
