package com.sosop.service.mail.business.imp;

import org.springframework.stereotype.Service;

import com.sosop.service.mail.business.MailBus;

@Service("mailBus")
public class MailBusImp implements MailBus {

	@Override
	public String sendMail(String type, int attach) {
		return type + " " + attach;
	}

}
