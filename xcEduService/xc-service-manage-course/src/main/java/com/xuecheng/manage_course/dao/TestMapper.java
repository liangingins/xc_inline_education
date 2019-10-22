package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<CourseBase> findList();
}
