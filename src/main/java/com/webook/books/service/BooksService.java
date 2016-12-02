package com.webook.books.service;


import com.webook.books.domain.Books;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
public interface BooksService {
    public List<Books> findAll();
    public Books findByBookSeq(int seq);
    public List<Books> findByBooksKindCd(String books_kind_cd);
    public void save(Books books);
    public void save_enctype(Books books);
    public void update(Books books);
}
