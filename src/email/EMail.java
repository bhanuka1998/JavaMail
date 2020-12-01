/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class EMail {
    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void main(String[] args) throws Exception {  
        EMail mail = new EMail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();

    }

    private void setupServerProperties() {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.ort", "547");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(prop, null);
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException {
        String [] emailRecipients = {"cb007874@students.apiit.lk", "cb008097@students.apiit.lk"};
        String emailSubject = "Test Mail";
        String emailBody = "This is successful.......";
        mimeMessage = new MimeMessage(newSession);
        
        for (int i = 0; i<emailRecipients.length; i++)
        {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress (emailRecipients[i]));
        }
        
        MimeBodyPart mimebody = new MimeBodyPart();
        mimebody.setContent(emailBody, "tml/text");
        
        mimeMessage.setSubject(emailSubject);
        MimeMultipart multipart = new MimeMultipart();
        mimeMessage.setContent(multipart);
        return mimeMessage;
        
        
    }

    private void sendEmail() throws MessagingException {
        String fromUser = "bhanuka06@gmail.com";
        String fromUserPw = "12360000";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPw);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Success");
    }
}

