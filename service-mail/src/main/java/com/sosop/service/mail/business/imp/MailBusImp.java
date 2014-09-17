package com.sosop.service.mail.business.imp;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sosop.service.mail.action.SendMail;
import com.sosop.service.mail.business.MailBus;
import com.sosop.service.mail.vo.Mail;
import com.sosop.service.mail.vo.Respond;

@Service("mailBus")
public class MailBusImp implements MailBus {

	@Resource(name = "sendMail")
	private SendMail sendMail;

	@Override
	public Respond sendMail(Mail mail) {
		int code = Respond.STATUS.UNSEND_CODE;
		int type = Arrays.binarySearch(Respond.TYPE.TYPES, mail.getType());
		if (mail.legal()) {
			if (mail.getType() == Respond.TYPE.TYPES[0]) {
				code = sendMail.sendTextMail(mail) ? Respond.STATUS.SUCCESS_CODE
						: Respond.STATUS.FALIUR_CODE;
			} else if (mail.getType() == Respond.TYPE.TYPES[1]) {
				code = sendMail.sendHtmlMail(mail) ? Respond.STATUS.SUCCESS_CODE
						: Respond.STATUS.FALIUR_CODE;
			} else if (mail.getType() == Respond.TYPE.TYPES[2]) {
				code = sendMail.sendAttach(mail) ? Respond.STATUS.SUCCESS_CODE
						: Respond.STATUS.FALIUR_CODE;
			}
		}
		return new Respond(code, Respond.TYPE.TYPES_NAME[type]);
	}
}
