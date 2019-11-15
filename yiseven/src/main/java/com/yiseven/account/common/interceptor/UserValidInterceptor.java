package com.yiseven.account.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdeng
 */
@Component
@Slf4j
public class UserValidInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (null != request.getHeader(Const.VALID_HEARER)) {
            if (null != redisUtil.get(request.getHeader(Const.VALID_HEARER))) {
                return true;
            }
            //请先登录
            log.error("未登录");
            needLogin(response);
            return false;
        }
        //缺少header
        log.error("缺少头部");
        needHeader(response);
        return false;
    }

    private void needLogin(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JSON.toJSONString(Response.createByErrorCode(ResponseCode.NEED_LOGIN)));
    }

    private void needHeader(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JSON.toJSONString(Response.createByErrorCode(ResponseCode.HEADER_LACK)));
    }
}
