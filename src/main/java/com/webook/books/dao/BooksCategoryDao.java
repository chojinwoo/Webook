package com.webook.books.dao;

import com.webook.books.domain.BooksCategory;

import java.util.List;

/**
 * Created by bangae1 on 2016-12-01.
 */
public interface BooksCategoryDao {
    public List<BooksCategory> findAll();
}
