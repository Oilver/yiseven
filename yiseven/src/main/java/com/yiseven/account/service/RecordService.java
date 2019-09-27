package com.yiseven.account.service;


import com.yiseven.account.common.response.Response;
import com.yiseven.account.entity.RecordEntity;
import com.yiseven.account.web.request.RecordRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdeng
 */
public interface RecordService {

    Response insert(RecordRequest recordRequest, HttpServletRequest request);

    Response delete(int id);

    Response queryList(HttpServletRequest request);
}
