package com.yiseven.account.common.exception;

import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hdeng
 */
@Controller
public class InterfaceErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 处理404
     * @return
     */
    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public Response errorPage() {
        return Response.createByErrorCode(ResponseCode.URL_ERROR);
    }
}
