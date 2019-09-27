package com.yiseven.account.common.exception;


import com.yiseven.account.common.response.ResponseCode;

/**
 * @author hdeng
 */
public class ExceptionThrow {
    public static void cast(ResponseCode responseCode, boolean condition) {
        if (condition) {
            throw new CustomException(responseCode);
        }
    }
}
