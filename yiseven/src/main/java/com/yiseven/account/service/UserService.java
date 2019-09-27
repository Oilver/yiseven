package com.yiseven.account.service;


import com.yiseven.account.common.response.Response;
import com.yiseven.account.entity.UserEntity;
import com.yiseven.account.web.request.UserRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdeng
 */
public interface UserService {

    Response addUser(UserRequest userRequest);

    Response updateUser(UserEntity userEntity);

    Response queryUser(String phone);

    Response queryCurrentUser(HttpServletRequest request);

    Response queryUserList();

    Response delete(int id);
}
