package com.webook.person.domain;

/**
 * Created by bangae1 on 2016-11-26.
 */
public class PersonAuth {
    private String id;
    private String authority;

    public PersonAuth() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "PersonAuth{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
