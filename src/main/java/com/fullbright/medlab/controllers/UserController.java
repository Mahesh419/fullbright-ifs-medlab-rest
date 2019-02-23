package com.fullbright.medlab.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullbright.medlab.entities.User;
import com.fullbright.medlab.models.UserModel;
import com.fullbright.medlab.repositories.UserRepository;

@Component
@Path("/user")
@CrossOrigin(allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS}, exposedHeaders = "*", origins = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Context
	private HttpServletResponse servletResponse;

	private void allowCrossDomainAccess() {
	    if (servletResponse != null) {
	        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
	    }
	}

	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin(allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, exposedHeaders = "*", origins = "*")
	public Response verifyLogin(User user) {	
		//allowCrossDomainAccess();
		User verifiedUser = userRepository.findUser(user.getUsername(), user.getPassword());
		
		boolean status = false;
		String userType = "";
		String userName = "";
		
		if(verifiedUser != null) {
			status = true;
			userType = verifiedUser.getUserType();
			userName = verifiedUser.getUsername();		
		}

		String response = "{\"status\": \"" + status + "\", \"username\": \"" + userName + "\", \"user_type\": \"" + userType + "\"}";
		return Response.status(Response.Status.OK).entity(response).build();
	}
	
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addUser(User user) {
//		userRepository.save(user);
//		return Response.status(Response.Status.OK).build();
//	}
	
	
}
