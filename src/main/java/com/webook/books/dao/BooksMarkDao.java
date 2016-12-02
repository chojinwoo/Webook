package com.webook.books.dao;


import com.webook.books.domain.BooksMark;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface BooksMarkDao {
    public void save(BooksMark booksMark);
    public List<BooksMark> findByBooks_seq(String id, int seq);
    public void delete(BooksMark booksMark);
}
