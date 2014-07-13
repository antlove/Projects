package helper;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import pojo.Attachment;
import pojo.Email;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class MailOperator {
	private static MailOperator mailOperator = new MailOperator();
	
	private MailOperator() {
	}

	public static MailOperator getInstance() {
		return mailOperator;
	}
	private Properties getMailProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");
		return props;
	}
	
	private Message getMailMessage(){
		Properties props = getMailProperties();
		// get the authenticator based on the user name and password
		DefaultAuthenticator authenticator = new DefaultAuthenticator("username", "password");

		// construct the session
		Session sendMailSession = Session.getDefaultInstance(props,authenticator);
		sendMailSession.setDebug(true);
		
		// create the message
		Message mailMessage = new MimeMessage(sendMailSession);
		return mailMessage;
	}
	
	private Multipart getMailContent(String body,List<Attachment> attachments) throws MessagingException, UnsupportedEncodingException{
		// add the text content
		Multipart multipart = new MimeMultipart();
		BodyPart textBodyPart = new MimeBodyPart();
		// textBodyPart.setText(body);
		// set the email type as html
		textBodyPart.setContent(body, "text/html;charset=utf-8");
		multipart.addBodyPart(textBodyPart);
        
		// add the attachment
        for(Attachment attachment : attachments){
        	String attachmentName = attachment.getAttachmentName();
        	String attachmentLocation = attachment.getAttachmentLocation();
            BodyPart attachmentBodyPart= new MimeBodyPart();
            
            // resolve the Chinese characters
			BASE64Encoder enc = new BASE64Encoder();
			attachmentBodyPart.setFileName("=?GBK?B?"+ enc.encode(attachmentName.getBytes()) + "?=");
			DataSource source = new FileDataSource(attachmentLocation);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
        	
        	multipart.addBodyPart(attachmentBodyPart);
        }
		return multipart;
	}

	public void sendTextMail(Email email) throws Exception{
		String sender = email.getSender();
		String senderName = email.getSenderName();
		String subject = email.getSubject();
		String body = email.getBody();
		List<String> receivers = email.getReceivers();
		List<String> ccList = email.getCcList();
		List<String> bccList = email.getBccList();
		List<Attachment> attachments = email.getAttachments();
		
		// get the mail message
		Message mailMessage = getMailMessage();
		
		// set the sender information
		Address from = new InternetAddress(sender,senderName);
		mailMessage.setFrom(from);
		
		// set the receivers informations
		for(String receiver : receivers){
			Address to = new InternetAddress(receiver);
			mailMessage.setRecipient(Message.RecipientType.TO, to);
		}
		
		// set the carbon copy information
		for(String cc : ccList){
			Address to = new InternetAddress(cc);
			mailMessage.setRecipient(Message.RecipientType.CC, to);
		}
		
		// set the blind carbon copy information
		for(String bcc : bccList){
			Address to = new InternetAddress(bcc);
			mailMessage.setRecipient(Message.RecipientType.BCC, to);
		}

		// set the subject information
		mailMessage.setSubject(subject);
		
		// set the send time
		mailMessage.setSentDate(new Date());
		
		// set the email body information
		Multipart multipart = getMailContent(body, attachments);
		mailMessage.setContent(multipart);
		
		// send the information
		Transport.send(mailMessage);
	}
}
