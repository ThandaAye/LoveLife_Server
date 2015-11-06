package com.lovelife.service.REST;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lovelife.model.UserDTO;
import com.lovelife.resource.Constant;
import com.lovelife.service.LoginService;
import com.lovelife.service.RegisterService;
import com.lovelife.service.RetrieveMemberService;

@Path("/RESTService")
public class RESTAPI {
	
	@GET
	@Path("/getAllMembers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){
		RetrieveMemberService service = new RetrieveMemberService();
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		usersList = service.getAllMembers();
		return Response.ok(usersList).build();
	}
	@POST
	@Path("/getMember")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMember(String userStr){
		RetrieveMemberService service = new RetrieveMemberService();
		UserDTO retrievedUser = new UserDTO();
		UserDTO attemptedUser = new UserDTO();
		JsonReader jsonReader = Json.createReader(new StringReader(userStr));
		JsonObject userJSON = jsonReader.readObject();
		jsonReader.close();
		attemptedUser.setUsername(userJSON.getString(Constant.J_USERNAME));
		attemptedUser.setPassword(userJSON.getString(Constant.J_PASSWORD));
		retrievedUser = service.getMember(attemptedUser);
		return Response.ok(retrievedUser).build();
	}
	
	//Login using GET method // not used for now
	@GET
	@Path("/login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogin(@PathParam("username") String username,@PathParam("password") String password){
		LoginService service = new LoginService();
		UserDTO authenticatedUser = new UserDTO();
		UserDTO attemptedUser = new UserDTO();
		attemptedUser.setUsername(username);
		attemptedUser.setPassword(password);
		authenticatedUser = service.loginUser(attemptedUser);
		return Response.ok(authenticatedUser).build();
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postLogin(String userStr){
		LoginService service = new LoginService();
		UserDTO authenticatedUser = new UserDTO();
		UserDTO attemptedUser = new UserDTO();
		JsonReader jsonReader = Json.createReader(new StringReader(userStr));
		JsonObject userJSON = jsonReader.readObject();
		jsonReader.close();
		attemptedUser.setUsername(userJSON.getString(Constant.J_USERNAME));
		attemptedUser.setPassword(userJSON.getString(Constant.J_PASSWORD));
		authenticatedUser = service.loginUser(attemptedUser);
		return Response.ok(authenticatedUser).build();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerAccount(String userStr) {
		RegisterService service = new RegisterService();
		UserDTO attemptedUser = new UserDTO();
		JsonReader jsonReader = Json.createReader(new StringReader(userStr));
		JsonObject userJSON = jsonReader.readObject();
		jsonReader.close();
		attemptedUser.setUsername(userJSON.getString(Constant.J_USERNAME));
		attemptedUser.setPassword(userJSON.getString(Constant.J_PASSWORD));
		attemptedUser.setFullName(userJSON.getString(Constant.J_FULLNAME));
		attemptedUser.setNickName(userJSON.getString(Constant.J_NICKNAME));
		attemptedUser.setDateOfBirth(userJSON.getString(Constant.J_DOB));
		attemptedUser.setContactNo(userJSON.getString(Constant.J_CONTACT));
		attemptedUser.setEmail(userJSON.getString(Constant.J_EMAIL));
		attemptedUser.setImgUrl(userJSON.getString(Constant.J_IMG));
		boolean result = service.registerAccount(attemptedUser);
		return Response.ok(result).build();
	}
}
