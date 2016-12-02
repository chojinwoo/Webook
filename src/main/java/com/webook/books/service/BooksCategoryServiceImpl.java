package com.webook.books.service;

import com.webook.books.dao.BooksCategoryDao;
import com.webook.books.domain.BooksCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bangae1 on 2016-12-01.
 */
@Service
public class BooksCategoryServiceImpl implements BooksCategoryService {
    @Autowired
    private BooksCategoryDao booksCategoryDao;

    @Override
    public List<BooksCategory> findAll() {
        return booksCategoryDao.findAll();
    }
}
