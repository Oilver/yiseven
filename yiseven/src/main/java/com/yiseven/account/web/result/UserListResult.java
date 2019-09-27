package com.yiseven.account.web.result;

import com.yiseven.account.entity.UserEntity;
import lombok.Data;

import java.util.List;

/**
 * @author hdeng
 */
@Data
public class UserListResult {

    List<UserEntity> userList;

    List<UserEntity> unPassList;

}
