package com.webook.books.service;

import com.webook.books.dao.BooksMarkDao;
import com.webook.books.domain.BooksMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
public class BooksMarkServiceImpl implements BooksMarkService {
    @Autowired
    private BooksMarkDao booksMarkDao;

    @Override
    public void save(BooksMark booksMark) {
        booksMarkDao.save(booksMark);
    }

    @Override
    public List<BooksMark> findByBooks_seq(String id, int seq) {
        return booksMarkDao.findByBooks_seq(id, seq);
    }

    @Override
    public void delete(BooksMark booksMark) {
        this.booksMarkDao.delete(booksMark);
    }
}
