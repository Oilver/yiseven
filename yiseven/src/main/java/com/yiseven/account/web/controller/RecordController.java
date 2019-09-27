package com.yiseven.account.web.controller;

import com.yiseven.account.common.response.Response;
import com.yiseven.account.entity.RecordEntity;
import com.yiseven.account.service.RecordService;
import com.yiseven.account.web.request.RecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author hdeng
 */
@RestController
@RequestMapping("record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("queryList")
    public Response queryList(HttpServletRequest request) {
        return recordService.queryList(request);
    }

    @PostMapping("add")
    public Response add(@Valid @RequestBody RecordRequest recordRequest, HttpServletRequest request) {
        return recordService.insert(recordRequest,request);
    }

}
