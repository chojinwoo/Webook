package com.webook.books.dao;

import com.webook.books.domain.Books;
import com.webook.books.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class BooksDaoImpl implements BooksDao {

    @Autowired
    private BooksMapper booksMapper;

    @Override
    public List<Books> findAll() {
        return booksMapper.findAll();
    }

    @Override
    public Books findByBookSeq(int seq) {
        return this.booksMapper.findByBookSeq(seq);
    }

    @Override
    public List<Books> findByBooksKindCd(String books_kind_cd) {
        return booksMapper.findByBooksKindCd(books_kind_cd);
    }

    @Override
    public void save(Books books) {
        this.booksMapper.save(books);
    }

    @Override
    public void update(Books books) {
        this.booksMapper.update(books);
    }
}
