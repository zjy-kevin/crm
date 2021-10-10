package com.yue.crm.settings.service;

import com.yue.crm.exception.LoginException;
import com.yue.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
