package com.union.common.utils.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Example基础Dao接口
 */
public interface BaseExampleMapper<T,V> {
    int countByExample(V example);

    int deleteByExample(V example);

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(V example);

    T selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") V example);

    int updateByExample(@Param("record") T record, @Param("example") V example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
