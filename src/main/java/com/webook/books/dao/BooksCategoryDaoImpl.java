package com.webook.books.dao;

import com.webook.books.domain.BooksCategory;
import com.webook.books.mapper.BooksCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by bangae1 on 2016-12-01.
 */
@Controller
public class BooksCategoryDaoImpl implements BooksCategoryDao {
    @Autowired
    private BooksCategoryMapper booksCategoryMapper;

    @Override
    public List<BooksCategory> findAll() {
        return booksCategoryMapper.findAll();
    }
}
