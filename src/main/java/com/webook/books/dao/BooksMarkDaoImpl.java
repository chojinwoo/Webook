package com.webook.books.dao;

import com.webook.books.domain.BooksMark;
import com.webook.books.mapper.BooksMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class BooksMarkDaoImpl implements BooksMarkDao {
    @Autowired
    private BooksMarkMapper booksMarkMapper;

    @Override
    public void save(BooksMark booksMark) {
        booksMarkMapper.save(booksMark);
    }

    @Override
    public List<BooksMark> findByBooks_seq(String id, int seq) {
        return booksMarkMapper.findByBooks_seq(id, seq);
    }

    @Override
    public void delete(BooksMark booksMark) {
        this.booksMarkMapper.delete(booksMark);
    }
}
