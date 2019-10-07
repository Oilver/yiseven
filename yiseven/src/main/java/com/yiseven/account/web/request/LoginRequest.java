package com.yiseven.account.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hdeng
 */
@Data
public class LoginRequest {
    @NotNull
    private String phone;

    @NotNull
    private String password;
}
