package com.yiseven.account.mapper.auto;

import com.yiseven.account.entity.RecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordEntity record);

    int insertSelective(RecordEntity record);

    RecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordEntity record);

    int updateByPrimaryKey(RecordEntity record);
}