package com.sosop.service.mail.vo;

public class Respond {
	public abstract class STATUS {
		public final static int SUCCESS_CODE = 200;
		public final static int NORMAL_CODE  = 0;
		public final static int FALIUR_CODE  = 500;
	}
	
	public abstract class TYPE {
		public final static String TEXT_TYPE = "text";
		public final static String HTML_TYPE = "html";
		public final static String ATTH_TYPE = "html-attach";
	}
	
	private int code;
	private String type;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}