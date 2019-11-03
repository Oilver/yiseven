package com.yiseven.account.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.common.util.HttpUtils;
import com.yiseven.account.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdeng
 * 只有管理员才有权利修改record
 */
@Component
public class AddRecordValidInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        UserEntity userEntity = HttpUtils.getUser(request);
        if (userEntity.getRole() == Const.ADMIN) {
            return true;
        }
        noRight(response);
        return false;
    }

    private void noRight(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JSON.toJSONString(Response.createByErrorCode(ResponseCode.NO_POWER)));
    }

}