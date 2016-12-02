package com.webook.person.dao;

import com.webook.person.domain.PersonAuth;
import com.webook.person.mapper.PersonAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class PersonAuthDaoImpl implements PersonAuthDao {
    @Autowired
    private PersonAuthMapper personAuthMapper;

    @Override
    public PersonAuth findById(String id) {
        return personAuthMapper.findById(id);
    }
}
