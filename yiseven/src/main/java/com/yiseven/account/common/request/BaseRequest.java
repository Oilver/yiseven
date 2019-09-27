package com.yiseven.account.common.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author hdeng
 */
@Data
public class BaseRequest {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private Date createDate;

    @JsonIgnore
    private Date lastUpdateDate;
}
