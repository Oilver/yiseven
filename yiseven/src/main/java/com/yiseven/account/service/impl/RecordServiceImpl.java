package com.yiseven.account.service.impl;


import com.yiseven.account.common.Const.Const;
import com.yiseven.account.common.exception.ExceptionThrow;
import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import com.yiseven.account.common.util.HttpUtils;
import com.yiseven.account.entity.AccountEntity;
import com.yiseven.account.entity.RecordEntity;
import com.yiseven.account.entity.UserEntity;
import com.yiseven.account.mapper.ext.AccountEntityMapperExt;
import com.yiseven.account.mapper.ext.RecordEntityMapperExt;
import com.yiseven.account.service.RecordService;
import com.yiseven.account.web.request.RecordRequest;
import com.yiseven.account.web.result.QueryAllResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author hdeng
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordEntityMapperExt recordEntityMapperExt;

    @Autowired
    AccountEntityMapperExt accountEntityMapperExt;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insert(RecordRequest recordRequest, HttpServletRequest request) {
        UserEntity user = HttpUtils.getUser(request);
        recordRequest.setCreateDate(new Date());
        recordRequest.setLastUpdateDate(new Date());

        //处理余额
        AccountEntity accountEntity = accountEntityMapperExt.selectByPrimaryKey(Const.ACCOUNT);
        double balance = accountEntity.getBalance();
        if (Const.INCOME_MONEY == recordRequest.getType()) {
            balance += recordRequest.getValue();
        } else if (Const.SPEND_MONEY == recordRequest.getType()) {
            balance -= recordRequest.getValue();
        }
        accountEntity.setBalance(balance);
        //修改余额
        int updateResult = accountEntityMapperExt.updateByPrimaryKeySelective(accountEntity);
        //失败了就抛出异常
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != updateResult);

        RecordEntity recordEntity = new RecordEntity();
        modelMapper.map(recordRequest, recordEntity);
        recordEntity.setCreateBy(user.getUsername());
        int result = recordEntityMapperExt.insertSelective(recordEntity);
        //失败了就抛出异常
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != result);

        return Response.createBySuccess();
    }

    @Override
    public Response delete(int id) {
        int result = recordEntityMapperExt.deleteByPrimaryKey(id);
        if (1 == result) {
            return Response.createBySuccess();
        }
        return Response.createByErrorMessage("删除失败");
    }

    @Override
    public Response queryList(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute(request.getHeader(Const.VALID_HEARER));
        if (user == null) {
            return Response.createByErrorCode(ResponseCode.NULL_EXCEPTION);
        }

        QueryAllResult queryAllResult = new QueryAllResult();
        queryAllResult.setUserRole(user.getRole());
        queryAllResult.setRecordEntityList(recordEntityMapperExt.queryList());
        queryAllResult.setBalance(accountEntityMapperExt.selectByPrimaryKey(Const.ACCOUNT).getBalance());
        return Response.createBySuccess(queryAllResult);
    }

}
