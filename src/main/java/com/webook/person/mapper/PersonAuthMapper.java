package com.webook.person.mapper;

import com.webook.person.domain.PersonAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Mapper
public interface PersonAuthMapper {
    @Select("select * from person_auth where id = #{id}")
    public PersonAuth findById(String id);
}
