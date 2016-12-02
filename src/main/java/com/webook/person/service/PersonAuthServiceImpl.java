package com.webook.person.service;

import com.webook.person.domain.PersonAuth;
import com.webook.person.mapper.PersonAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
public class PersonAuthServiceImpl implements PersonAuthService{
    @Autowired
    private PersonAuthMapper personAuthMapper;

    @Override
    public PersonAuth findById(String id) {
        return personAuthMapper.findById(id);
    }
}
