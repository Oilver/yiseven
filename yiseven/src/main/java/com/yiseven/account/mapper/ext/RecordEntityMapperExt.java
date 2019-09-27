package com.yiseven.account.mapper.ext;


import com.yiseven.account.entity.RecordEntity;
import com.yiseven.account.mapper.auto.RecordEntityMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hdeng
 */
@Mapper
public interface RecordEntityMapperExt extends RecordEntityMapper {
    List<RecordEntity> queryList();
}