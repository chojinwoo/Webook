package com.webook.person.service;

import com.webook.person.dao.PersonAuthDao;
import com.webook.person.dao.PersonDao;
import com.webook.person.domain.Person;
import com.webook.person.domain.PersonAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonAuthDao personAuthDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDao.loadUserByUsername(username);
        PersonAuth personAuth = personAuthDao.findById(person.getId());
        HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        final String auth = personAuth.getAuthority();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return auth;
            }
        });
        person.setAuthorities(authorities);
        return person;
    }
}
