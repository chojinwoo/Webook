package com.webook.person.mapper;

import com.webook.person.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface PersonMapper {
    @Select("select * from person where id = #{id}")
    public Person findById(@Param("id") String id);
}
