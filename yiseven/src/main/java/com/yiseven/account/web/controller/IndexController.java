package com.yiseven.account.web.controller;

import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.util.RedisUtil;
import com.yiseven.account.service.IndexService;
import com.yiseven.account.web.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author hdeng
 */
@RestController
public class IndexController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    IndexService indexService;

    @PostMapping("login")
    public Response login(@Valid @RequestBody LoginRequest loginRequest) {
        return indexService.login(loginRequest);
    }

    @PostMapping("logout")
    public Response logout(HttpServletRequest request) {
        redisUtil.del(request.getHeader(Const.VALID_HEARER));
        return Response.createBySuccess();
    }
}
