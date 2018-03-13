package com.learn.core.dao;

import com.learn.core.bean.School;
import com.learn.core.bean.SchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolMapper {
    long countByExample(SchoolExample example);

    int deleteByExample(SchoolExample example);

    int insert(School record);

    int insertSelective(School record);

    List<School> selectByExample(SchoolExample example);

    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);
}