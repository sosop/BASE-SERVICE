package com.sosop.service.mail.business;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/mail")
public interface MailBus {
	
	@GET
	@Path("/send/{type}/{attach}")
	/*@Consumes(MediaType.APPLICATION_JSON)*/
	@Produces(MediaType.APPLICATION_JSON)
	public String sendMail(@PathParam("type") String type, @PathParam("attach") int attach);
}
