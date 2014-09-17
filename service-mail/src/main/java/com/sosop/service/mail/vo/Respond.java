package com.sosop.service.mail.vo;

public class Respond {
	public abstract class STATUS {
		public final static int SUCCESS_CODE = 200;
		public final static int UNSEND_CODE  = 0;
		public final static int FALIUR_CODE  = 500;
	}
	
	public static abstract class TYPE {
		public final static int[]    TYPES      = {1, 2, 3};
		public final static String[] TYPES_NAME = {"text", "html", "attach"};
	}
	
	public Respond() {}
	
	public Respond(int code, String type) {
		super();
		this.code = code;
		this.type = type;
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