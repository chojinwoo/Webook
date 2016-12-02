package com.webook.books.dao;

import com.webook.books.domain.BooksLog;
import com.webook.books.mapper.BooksLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Repository
public class BooksLogDaoImpl implements BooksLogDao {
    @Autowired
    private BooksLogMapper booksLogMapper;

    @Override
    public void save(BooksLog booksLog) {
        booksLogMapper.save(booksLog);
    }

    @Override
    public List<BooksLog> findByid(String id) {
        return booksLogMapper.findByid(id);
    }

    @Override
    public List<BooksLog> findAll(String id) {
        return this.booksLogMapper.findAll(id);
    }
}
