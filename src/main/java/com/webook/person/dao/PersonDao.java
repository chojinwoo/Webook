package com.webook.person.dao;

import com.webook.person.domain.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface PersonDao {
    Person loadUserByUsername(String username) throws UsernameNotFoundException;
}
