package com.webook.books.dao;


import com.webook.books.domain.BooksKind;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface BooksKindDao {
    public List<BooksKind> findAll();
    public BooksKind findOne(String books_kind_cd);
    public void save(BooksKind booksKind);
    public void update(BooksKind booksKind);
}
