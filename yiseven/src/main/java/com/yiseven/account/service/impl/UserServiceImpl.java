package com.yiseven.account.service.impl;

import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.exception.ExceptionThrow;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.common.util.HttpUtils;
import com.yiseven.account.common.util.MD5Utils;
import com.yiseven.account.entity.UserEntity;
import com.yiseven.account.mapper.ext.UserEntityMapperExt;
import com.yiseven.account.service.UserService;
import com.yiseven.account.web.request.UserRequest;
import com.yiseven.account.web.result.UserListResult;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author hdeng
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityMapperExt userEntityMapperExt;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response addUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        modelMapper.map(userRequest, userEntity);
        userEntity.setStatus(Const.REVIEW_STATUS);
        userEntity.setCreateDate(new Date());
        userEntity.setLastUpdateDate(new Date());
        userEntity.setPassword(MD5Utils.getMd5Simple(userEntity.getPassword()));
        UserEntity queryResult = userEntityMapperExt.queryUser(userEntity.getPhone());
        if (queryResult != null) {
            return Response.createByErrorMessage("该手机已经被注册");
        }
        int result = userEntityMapperExt.insertSelective(userEntity);
        if (Const.INSERT_ONE == result) {
            log.info("用户 {} 注册成功", userEntity.getUsername() + " " + userEntity.getPhone());
            return Response.createBySuccess();
        }
        return Response.createByErrorMessage("插入数据失败");
    }

    @Override
    public Response updateUser(UserEntity userEntity) {
        int resultCount = userEntityMapperExt.updateByPrimaryKeySelective(userEntity);
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != resultCount);
        return Response.createBySuccess();
    }

    @Override
    public Response queryUser(String phone) {
        UserEntity userEntity = userEntityMapperExt.queryUser(phone);
        ExceptionThrow.cast(ResponseCode.RESULT_NULL, null == userEntity);
        return Response.createBySuccess(userEntity);
    }

    @Override
    public Response queryCurrentUser(HttpServletRequest request) {
        UserEntity userEntity = HttpUtils.getUser(request);
        if (userEntity != null) {
            return Response.createBySuccess(userEntity);
        }
        return Response.createBySuccess(userEntityMapperExt.queryUser(request.getHeader(Const.VALID_HEARER)));
    }

    @Override
    public Response queryUserList() {
        UserListResult userListResult = new UserListResult();
        userListResult.setUserList(userEntityMapperExt.queryUserList(Const.ACTIVE_STATUS));
        userListResult.setUnPassList(userEntityMapperExt.queryUserList(Const.REVIEW_STATUS));
        return Response.createBySuccess(userListResult);
    }

    @Override
    public Response delete(int id) {
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != userEntityMapperExt.deleteByPrimaryKey(id));
        return Response.createBySuccess();
    }
}
