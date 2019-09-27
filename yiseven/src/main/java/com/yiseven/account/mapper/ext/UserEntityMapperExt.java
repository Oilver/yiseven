package com.yiseven.account.mapper.ext;

import com.yiseven.account.entity.UserEntity;
import com.yiseven.account.mapper.auto.UserEntityMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hdeng
 */
@Mapper
public interface UserEntityMapperExt extends UserEntityMapper {

    UserEntity queryUser(String phone);

    List<UserEntity> queryUserList(int status);

    int queryUserByPassword(@Param("phone") String phone, @Param("password") String password);
}