package com.sosop.service.mail.business.imp;

import org.springframework.stereotype.Service;

import com.sosop.service.mail.business.MailBus;
import com.sosop.service.mail.vo.Mail;
import com.sosop.service.mail.vo.Respond;

@Service("mailBus")
public class MailBusImp implements MailBus {

	@Override
	public Respond sendMail(Mail mail) {
		return new Respond();
	}

}
