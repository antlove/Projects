package test;

import helper.MailOperator;
import pojo.Attachment;
import pojo.Email;

public class Main {

	public static void main(String[] args) throws Exception{
		Email email = new Email();
		email.setSender("sender email address");
		email.setSubject("email subject");
		email.setBody("<br/><font color ='red'> email body</font>");
		email.getReceivers().add("receiver address");
		email.getAttachments().add(new Attachment("attachmentName","attachment location in local server"));
		
		MailOperator.getInstance().sendTextMail(email);
	}

}
