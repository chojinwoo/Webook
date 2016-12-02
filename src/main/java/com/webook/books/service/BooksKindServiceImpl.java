package com.webook.books.service;

import com.webook.books.dao.BooksKindDao;
import com.webook.books.domain.BooksKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
@PropertySource("classpath:application.properties")
public class BooksKindServiceImpl implements BooksKindService {
    @Autowired
    private BooksKindDao booksKindDao;

    @Autowired
    private Environment env;

    @Override
    public List<BooksKind> findAll() {
        return booksKindDao.findAll();
    }

    @Override
    public BooksKind findOne(String books_kind_cd) {
        return this.booksKindDao.findOne(books_kind_cd);
    }

    @Override
    public void save(BooksKind booksKind) {
        String path = env.getProperty("multipart.path") + booksKind.getBooks_kind_path();
        File filePath = new File(path);
        filePath.mkdir();
        this.booksKindDao.save(booksKind);
    }

    @Override
    public void update(BooksKind booksKind) {
        this.booksKindDao.update(booksKind);
    }
}
