/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultils;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DPV
 */
public class EmailUtils {

    final String fromEmail = "manhnkhe153424@fpt.edu.vn";
    final String password = "manh30072001@";
    final String systemName = "Quiz Practice System";
    final String groupName = "Group 05 - SE1616";

    public void send(String toEmail, String subject, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, systemName));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(message, "text/HTML; charset=UTF-8");

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mailConfirmAccount(String toEmail, String linkConfirm, String userName) {
        String subject = "Confirm Your Account";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h3>Wellcome, " + userName + "</h3>\n"
                + "    <div><i>** This is an automated message -- please do not reply as you will not receive a response. **</i></div><br>\n"
                + "    <div>Please confirm your account by clicking the link below:</div><br>\n"
                + "    <div><a href=\"" + linkConfirm + "\"><b>CONFIRM ACCOUNT</b></a></div><br>\n"
                + "    <div>Sincerely,</div>\n"
                + "    <div><b>" + groupName + "</b></div>\n"
                + "</body>\n"
                + "</html>";
        send(toEmail, subject, message);
    }

    public void mailResetPassword(String toEmail, String linkResetPassword, String userName) {
        String subject = "Reset Your Password";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h3>Hello, " + userName + "</h3>\n"
                + "    <div><i>** This is an automated message -- please do not reply as you will not receive a response. **</i></div><br>\n"
                + "    <div>You recently took steps to reset the password for your account. Click on the link below to reset your password.</div><br>\n"
                + "    <div><a href=\"" + linkResetPassword + "\"><b>RESET PASSWORD</b></a></div><br>\n"
                + "    <div>This link will expire in 5 minutes after this email was sent.</div><br>\n"
                + "    <div>Sincerely,</div>\n"
                + "    <div><b>" + groupName + "</b></div>\n"
                + "</body>\n"
                + "</html>";
        send(toEmail, subject, message);
    }

    public static void main(String[] args) {
        EmailUtils se = new EmailUtils();
        se.mailConfirmAccount("nguyenkhanhmanh9@gmail.com", "https://www.facebook.com/ducprovng/", "DPV");
        //se.mailResetPassword("thong2382001@gmail.com", "https://www.facebook.com/ducprovng/", "DPV");
    }
}
