package com.sosop.service.mail.business;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sosop.service.mail.vo.Mail;
import com.sosop.service.mail.vo.Respond;


@Path("/mail")
public interface MailBus {
	@POST
	@Path("/send")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Respond sendMail(Mail mail);
}
