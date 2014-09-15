package com.sosop.service.mail.vo;

import java.util.Properties;

public class Mail {

	private String fromAddress;
	private String[] toAddress;
	private String subject;
	private String content;
	private String[] attachFileNames;

	private Properties propeties;

	public Properties getPropeties() {
		return propeties;
	}

	public void setPropeties(Properties propeties) {
		this.propeties = propeties;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}