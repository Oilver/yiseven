package com.yiseven.account.common.util;

import com.yiseven.account.common.Const.Const;
import com.yiseven.account.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdeng
 */
public class HttpUtils {

    public static UserEntity getUser(HttpServletRequest request) {
        return (UserEntity) request.getSession().getAttribute(request.getHeader(Const.VALID_HEARER));
    }
}
