package com.sosop.service.mail.vo;

/**
 * 
 * @author sosop
 * @descibe mail类
 */
public class Mail {

	private String fromAddress;
	private String[] toAddress;
	private String subject;
	private String content;
	private String[] attachFileNames;
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}