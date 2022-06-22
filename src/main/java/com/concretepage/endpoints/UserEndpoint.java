package com.concretepage.endpoints;

import com.concretepage.entity.User;
import com.concretepage.gs_ws.*;
import com.concretepage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class UserEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/springsoap/jaxbModel";
	@Autowired
	private UserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse add(@RequestPayload AddUserRequest addUserRequest) {
		AddUserResponse userResponse = userService.addUser(addUserRequest.getUserInfo());
		return userResponse;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse update(@RequestPayload UpdateUserRequest updateUserRequest) {
		UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest.getUserInfo());
		return updateUserResponse;
	}


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse delete(@RequestPayload DeleteUserRequest deleteUserRequest) {
		DeleteUserResponse userResponse = userService.deleteUser(deleteUserRequest.getLogin());
		return userResponse;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByLoginRequest")
	@ResponsePayload
	public GetUserByLoginResponse get(@RequestPayload GetUserByLoginRequest getUserByLoginRequest) {
		return userService.getUser(getUserByLoginRequest.getLogin());
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
	@ResponsePayload
	public GetAllUsersResponse getAllUsers() {
		return userService.getAllUsers();
	}
}
