package com.webook.books.dao;

import com.webook.books.domain.BooksKind;
import com.webook.books.mapper.BooksKindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class BooksKindDaoImpl implements BooksKindDao {
    @Autowired
    private BooksKindMapper booksKindMapper;

    @Override
    public List<BooksKind> findAll() {
        return booksKindMapper.findAll();
    }

    @Override
    public BooksKind findOne(String books_kind_cd) {
        return this.booksKindMapper.findOne(books_kind_cd);
    }

    @Override
    public void save(BooksKind booksKind) {
        this.booksKindMapper.save(booksKind);
    }

    @Override
    public void update(BooksKind booksKind) {
        this.booksKindMapper.update(booksKind);
    }
}
