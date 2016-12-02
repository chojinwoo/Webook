package com.webook.books.dao;


import com.webook.books.domain.Books;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface BooksDao {
    public List<Books> findAll();
    public Books findByBookSeq(int seq);
    public List<Books> findByBooksKindCd(String boos_kind_cd);
    public void save(Books books);
    public void update(Books books);
}
