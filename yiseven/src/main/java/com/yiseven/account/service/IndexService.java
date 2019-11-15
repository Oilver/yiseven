package com.yiseven.account.service;

import com.yiseven.account.common.response.Response;
import com.yiseven.account.web.request.LoginRequest;

/**
 * @author hdeng
 */
public interface IndexService {

    Response login(LoginRequest loginRequest);
}
