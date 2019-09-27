package com.yiseven.account.service.impl;


import com.yiseven.account.common.exception.ExceptionThrow;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.entity.AccountEntity;
import com.yiseven.account.mapper.ext.AccountEntityMapperExt;
import com.yiseven.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hdeng
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountEntityMapperExt accountEntityMapperExt;

    @Override
    public Response query(int id) {
        AccountEntity accountEntity = accountEntityMapperExt.selectByPrimaryKey(id);
        ExceptionThrow.cast(ResponseCode.RESULT_NULL, null == accountEntity);
        return Response.createBySuccess(accountEntity);
    }

}
