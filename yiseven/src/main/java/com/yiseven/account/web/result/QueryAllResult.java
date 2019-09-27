package com.yiseven.account.web.result;


import com.yiseven.account.entity.RecordEntity;
import lombok.Data;

import java.util.List;

/**
 * @author hdeng
 */
@Data
public class QueryAllResult {

    private List<RecordEntity> recordEntityList;

    private Double balance;

    private Integer userRole;
}
