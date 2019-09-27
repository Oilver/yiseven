package com.yiseven.account.service.impl;

import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.exception.ExceptionThrow;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.entity.UserEntity;
import com.yiseven.account.mapper.ext.UserEntityMapperExt;
import com.yiseven.account.service.IndexService;
import com.yiseven.account.web.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdeng
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserEntityMapperExt userEntityMapperExt;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Response login(LoginRequest loginRequest, HttpServletRequest request) {
        UserEntity userEntity = userEntityMapperExt.queryUser(loginRequest.getPhone());
        if (userEntity == null) {
            return Response.createByErrorMessage("账户不存在");
        }
        int count = userEntityMapperExt.queryUserByPassword(loginRequest.getPhone(), loginRequest.getPassword());
        if (0 == count) {
            return Response.createByErrorMessage("密码错误");
        } else if (1 == count) {
            if (Const.ACTIVE_STATUS != userEntity.getStatus()) {
                return Response.createByErrorMessage("该账户还在审核...");
            }
            request.getSession().setAttribute(loginRequest.getPhone(), userEntity);
            log.info("用户 {} 登录成功", userEntity.getUsername() + " " + userEntity.getPhone());
            return Response.createBySuccess("登录成功", userEntity.getPhone());
        } else {
            ExceptionThrow.cast(ResponseCode.USER_WRONG, true);
        }
        return Response.createByErrorCode(ResponseCode.COMMON_ERROR);
    }
}
