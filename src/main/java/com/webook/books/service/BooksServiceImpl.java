package com.webook.books.service;

import com.webook.books.dao.BooksDao;
import com.webook.books.dao.BooksKindDao;
import com.webook.books.domain.Books;
import com.webook.books.domain.BooksKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Service
@PropertySource("classpath:application.properties")
public class BooksServiceImpl implements BooksService{
    @Autowired
    private BooksDao booksDao;

    @Autowired
    private BooksKindDao booksKindDao;

    @Autowired
    private Environment env;

    @Override
    public List<Books> findAll() {
        return booksDao.findAll();
    }

    @Override
    public Books findByBookSeq(int seq) {
        return this.booksDao.findByBookSeq(seq);
    }

    @Override
    public List<Books> findByBooksKindCd(String books_kind_cd) {
        return this.booksDao.findByBooksKindCd(books_kind_cd);
    }

    @Override
    public void save(Books books) {
        this.booksDao.save(books);
    }

    @Override
    @Transactional
    public void save_enctype(Books books) {
        books.setBooks_file_nm(books.getFile().getOriginalFilename());
        BooksKind booksKind = this.booksKindDao.findOne(books.getBooks_kind_cd());
        books.setBooks_url("/books"+booksKind.getBooks_kind_path() + "/"+ UUID.randomUUID()+".epub");
        this.booksDao.save(books);
        File filePath = new File(env.getProperty("multipart.path") + booksKind.getBooks_kind_path() + "/"+UUID.randomUUID()+".epub");
        try {
            books.getFile().transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Books books) {
        this.booksDao.update(books);
    }
}
