package com.concretepage.service;


import com.concretepage.entity.Role;
import com.concretepage.entity.User;
import com.concretepage.gs_ws.*;
import com.concretepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public AddUserResponse addUser(UserInfo userInfo) {
        List<RoleInfo> rolesInfo = userInfo.getRoleInfo();
        List<Role> roles = new ArrayList<>();

        String password = userInfo.getPassword();

        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setStatus("false");

        if (!password.matches(("^.*[A-Z].*$"))) {
            addUserResponse.getErrors().add("Invalid password by upper case latter");
            return addUserResponse;
        }

        if (!password.matches("^.*[0-9].*$")) {
            addUserResponse.getErrors().add("Invalid password digit");
            return addUserResponse;
        }


        for (RoleInfo roleInfo : rolesInfo) {
            Role role = new Role()
                    .setId(roleInfo.getId())
                    .setName(roleInfo.getName());
            roles.add(role);
        }

        User user = new User()
                .setLogin(userInfo.getLogin())
                .setName(userInfo.getName())
                .setPassword(userInfo.getPassword())
                .setRoles(roles);

        userRepository.save(user);
        addUserResponse.setStatus("true");
        return addUserResponse;
    }

    @Transactional
    public UpdateUserResponse updateUser(UserInfo userInfo) {
        List<RoleInfo> rolesInfo = userInfo.getRoleInfo();
        List<Role> roles = new ArrayList<>();

        String password = userInfo.getPassword();

        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setStatus("false");

        if (!password.matches(("^.*[A-Z].*$"))) {
            updateUserResponse.getErrors().add("Invalid password by upper case latter");
            return updateUserResponse;
        }

        if (!password.matches("^.*[0-9].*$")) {
            updateUserResponse.getErrors().add("Invalid password digit");
            return updateUserResponse;
        }

        for (RoleInfo roleInfo : rolesInfo) {
            Role role = new Role()
                    .setId(roleInfo.getId())
                    .setName(roleInfo.getName());
        }

        User user = new User()
                .setLogin(userInfo.getLogin())
                .setName(userInfo.getName())
                .setPassword(userInfo.getPassword())
                .setRoles(roles);

        userRepository.save(user);
        return updateUserResponse;
    }

    @Transactional
    public GetAllUsersResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserInfo> usersInfo = new ArrayList<>();
        GetAllUsersResponse getAllArticlesResponse = new GetAllUsersResponse();
        for (User user : users) {
            UserInfo userInfo = new UserInfo();
            userInfo.setLogin(user.getLogin());
            userInfo.setName(user.getName());
            userInfo.setPassword(user.getPassword());
            getAllArticlesResponse.getUserInfo().add(userInfo);
        }
        return getAllArticlesResponse;
    }

    @Transactional
    public GetUserByLoginResponse getUser(String login) {
        User user = userRepository.findByLogin(login);
        GetUserByLoginResponse userByLoginResponse = new GetUserByLoginResponse();
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(user.getLogin());
        userInfo.setName(user.getName());
        userInfo.setPassword(user.getPassword());
        userByLoginResponse.setUserInfo(userInfo);
        return userByLoginResponse;
    }

    @Transactional
    public DeleteUserResponse deleteUser(String login) {
        userRepository.delete(userRepository.findByLogin(login));
        DeleteUserResponse userResponse = new DeleteUserResponse();
        userResponse.setStatus("true");
        return userResponse;
    }
}
