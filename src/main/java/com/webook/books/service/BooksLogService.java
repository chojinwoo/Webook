package com.webook.books.service;


import com.webook.books.domain.BooksLog;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface BooksLogService {
    public void save(BooksLog booksLog);
    public List<BooksLog> findByid(String id);
    public List<BooksLog> findAll(String id);

}
