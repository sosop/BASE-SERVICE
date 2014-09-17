package com.sosop.service.mail.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sosop.service.common.util.StringUtil;

/**
 * 
 * @author sosop
 * @descibe mail类
 */
@XmlRootElement(name = "mail")
@XmlAccessorType(XmlAccessType.FIELD)  
public class Mail {

	private String fromAddress;
	private String[] toAddress;
	private String subject;
	private String content;
	private String[] attachFileNames;
	private int type;

	public Mail() {
		this.type = Respond.TYPE.TYPES[0];
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean legal() {
		return !StringUtil.isNull(toAddress);
	}
}