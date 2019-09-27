package com.yiseven.account.service;

import com.yiseven.account.common.response.Response;
import com.yiseven.account.web.request.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hdeng
 */
public interface IndexService {

    Response login(LoginRequest loginRequest, HttpServletRequest request);
}
