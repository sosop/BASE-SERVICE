package com.sosop.service.mail.action;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sosop.service.mail.auth.Authenticator;
import com.sosop.service.mail.vo.Mail;
/**
 * 
 * @author xiaolong.hou
 * @date 2014.9.15
 * @describe send email
 */
@Service("sendMail")
public class SendMail {

	private final static Logger LOG = Logger.getLogger(SendMail.class);

	@Resource(name = "mail")
	private Mail mail;

	/**
	 * 文本邮件
	 * @return
	 */
	public boolean sendTextMail() {
		Properties pro = mail.getPropeties();
		Session sendMailSession = Session.getDefaultInstance(pro, auth(pro));
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(pro.getProperty("from"));
			mailMessage.setFrom(from);
			String[] toArray = mail.getToAddress();
			if (toArray.length == 0)
				return false;
			InternetAddress[] toAddrArray = new InternetAddress[toArray.length];
			for (int i = 0; i < toArray.length; i++) {
				toAddrArray[i] = new InternetAddress(toArray[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, toAddrArray);
			mailMessage.setSubject(mail.getSubject());
			mailMessage.setSentDate(new Date());
			String mailContent = mail.getContent();
			mailMessage.setText(mailContent);
			Transport.send(mailMessage);
			LOG.info(mail);
			return true;
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * html 格式文件
	 * @return
	 */
	public boolean sendHtmlMail() {
		Properties pro = mail.getPropeties();
		Session sendMailSession = Session.getDefaultInstance(pro, auth(pro));
		sendMailSession.setDebug(true);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(pro.getProperty("from"));
			mailMessage.setFrom(from);
			String[] toArray = mail.getToAddress();
			if (toArray.length == 0)
				return false;
			Address[] toAddrArray = new InternetAddress[toArray.length];
			for (int i = 0; i < toArray.length; i++) {
				toAddrArray[i] = new InternetAddress(toArray[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, toAddrArray);
			mailMessage.setSubject(mail.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mail.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			mailMessage.saveChanges();
			Transport transport = sendMailSession.getTransport(pro.getProperty("mail.transport.procotol"));
			transport.connect(pro.getProperty("mail.smtp.host"),
					pro.getProperty("username"), pro.getProperty("password"));
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
			transport.close();
			LOG.info(mail);
			return true;
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 带附件邮件
	 * @return
	 */
	public boolean sendAttach() {
		Properties pro = mail.getPropeties();
		Session sendMailSession = Session.getDefaultInstance(pro, auth(pro));
		sendMailSession.setDebug(true);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(pro.getProperty("from"));
			mailMessage.setFrom(from);
			String[] toArray = mail.getToAddress();
			if (toArray.length == 0)
				return false;
			Address[] toAddrArray = new InternetAddress[toArray.length];
			for (int i = 0; i < toArray.length; i++) {
				toAddrArray[i] = new InternetAddress(toArray[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, toAddrArray);
			mailMessage.setSubject(mail.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();
			BodyPart mbp = new MimeBodyPart();
			mbp.setContent(mail.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(mbp);
			String attachFiles[] = mail.getAttachFileNames();
			for (String f : attachFiles) {
				mbp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(f);
				mbp.setDataHandler(new DataHandler(fds));
				mbp.setFileName(fds.getName());
				mainPart.addBodyPart(mbp);
			}
			mailMessage.setContent(mainPart);
			// mailMessage.saveChanges();
			// Transport transport = sendMailSession.getTransport("smtp");
			// transport.connect(mailInfo.getMailServerHost(),mailInfo.getUserName(),mailInfo.getPassword());
			// transport.sendMessage(mailMessage,
			// mailMessage.getAllRecipients());
			// transport.close();
			Transport.send(mailMessage);
			LOG.info(mail);
			return true;
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 权限
	 * @param pro
	 * @return
	 */
	private Authenticator auth(Properties pro) {
		Authenticator authenticator = null;
		if (Boolean.valueOf(pro.getProperty("mail.smtp.auth", "false"))) {
			authenticator = new Authenticator(pro.getProperty("username"),
					pro.getProperty("password"));
		}
		return authenticator;
	}
}