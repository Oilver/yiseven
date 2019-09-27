package com.yiseven.account.web.request;


import com.yiseven.account.common.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hdeng
 */
@Data
public class UserRequest extends BaseRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private Integer role;
}