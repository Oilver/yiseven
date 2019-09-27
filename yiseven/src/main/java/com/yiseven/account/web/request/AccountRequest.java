package com.yiseven.account.web.request;


import com.yiseven.account.common.request.BaseRequest;
import lombok.Data;

/**
 * @author hdeng
 */
@Data
public class AccountRequest extends BaseRequest {

    private Double balance;

}