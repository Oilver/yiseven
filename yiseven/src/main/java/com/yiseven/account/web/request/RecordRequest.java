package com.yiseven.account.web.request;


import com.yiseven.account.common.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hdeng
 */
@Data
public class RecordRequest extends BaseRequest {
    @NotNull
    private String title;

    private String description;

    @NotNull
    private Integer type;

    @NotNull
    private Double value;
}