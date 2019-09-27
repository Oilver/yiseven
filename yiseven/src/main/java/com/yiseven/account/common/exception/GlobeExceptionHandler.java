package com.yiseven.account.common.exception;

import com.yiseven.account.common.response.Response;
import com.yiseven.account.common.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * @author hdeng
 */
@ControllerAdvice
@Slf4j
public class GlobeExceptionHandler {

    private static HashMap<Class<? extends Throwable>, ResponseCode> EXCEPTIONS = new HashMap<>();

    //运行时异常
    static {
        EXCEPTIONS.put(NullPointerException.class, ResponseCode.NULL_EXCEPTION);
        EXCEPTIONS.put(ArithmeticException.class, ResponseCode.ARITH_EXCEPTION);
        EXCEPTIONS.put(BadSqlGrammarException.class, ResponseCode.DATABASE_ERROR);
        EXCEPTIONS.put(MethodArgumentNotValidException.class, ResponseCode.PARAM_WRONG);
    }

    @ExceptionHandler
    @ResponseBody
    public Response customException(CustomException e) {
        e.printStackTrace();
        log.error(getExceptionDetail(e));
        return Response.createByErrorCode(e.getResponseCode());
    }

    @ExceptionHandler
    @ResponseBody
    public Response exception(Exception e) {
        e.printStackTrace();
        log.error(getExceptionDetail(e));
        if (null == EXCEPTIONS.get(e.getClass())) {
            return Response.createByErrorCode(ResponseCode.COMMON_ERROR);
        } else {
            return Response.createByErrorCode(EXCEPTIONS.get(e.getClass()));
        }
    }

    /**
     * 获取异常详细信息，知道出了什么错，错在哪个类的第几行 .
     *
     * @param ex
     * @return
     */
    public static String getExceptionDetail(Exception ex) {
        String ret = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(out);
            ex.printStackTrace(pout);
            ret = new String(out.toByteArray());
            pout.close();
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }
}
