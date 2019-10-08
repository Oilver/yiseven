package com.yiseven.account.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hdeng
 */
@Component
public class UserValidInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        if (null != request.getHeader(Const.VALID_HEARER)) {
            System.out.println(request.getHeader(Const.VALID_HEARER) + "");
            if (null != session.getAttribute(request.getHeader(Const.VALID_HEARER) + "")) {
                return true;
            }
            //请先登录
            needLogin(response);
            return false;
        }
        //缺少header
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
