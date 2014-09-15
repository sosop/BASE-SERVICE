package com.sosop.service.mail.action;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.sosop.service.mail.vo.Mail;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SendMailTest extends AbstractJUnit4SpringContextTests {
	
	@Resource(name = "sendMail")
	private SendMail sendMail;
	
	private static Mail mail;
	
	@BeforeClass
	public static void init() {
		mail = new Mail();
		mail.setFromAddress(null);
		mail.setToAddress(new String[]{"sosopish@163.com"});
		mail.setSubject("it's a test");
		mail.setContent("<h1>I'm testing mail service</h1>");
		mail.setAttachFileNames(new String[]{"d:\\mail.txt"});
	}
	
	@Test
	public void testSendTextMail() {
		Assert.assertThat(sendMail.sendTextMail(mail), Matchers.is(true));
	}
	
	@Test
	public void testSendHtmlMail() {
		Assert.assertThat(sendMail.sendHtmlMail(mail), Matchers.is(true));
	}
	
	@Test
	public void testSendAttach() {
		Assert.assertThat(sendMail.sendAttach(mail), Matchers.is(true));
	}
}
