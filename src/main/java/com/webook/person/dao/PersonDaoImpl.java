package com.webook.person.dao;

import com.webook.person.domain.Person;
import com.webook.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person loadUserByUsername(String username) throws UsernameNotFoundException {
        return personMapper.findById(username);
    }
}
