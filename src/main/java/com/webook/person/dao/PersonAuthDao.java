package com.webook.person.dao;


import com.webook.person.domain.PersonAuth;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface PersonAuthDao {
    public PersonAuth findById(String id);
}
