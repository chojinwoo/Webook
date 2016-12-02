package com.webook.books.service;

import com.webook.books.dao.BooksLogDao;
import com.webook.books.domain.BooksLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
public class BooksLogServiceImpl implements BooksLogService {
    @Autowired
    private BooksLogDao booksLogDao;

    @Override
    public void save(BooksLog booksLog) {
        this.booksLogDao.save(booksLog);
    }

    @Override
    public List<BooksLog> findByid(String id) {
        return this.booksLogDao.findByid(id);
    }

    @Override
    public List<BooksLog> findAll(String id) {
        return this.booksLogDao.findAll(id);
    }
}
