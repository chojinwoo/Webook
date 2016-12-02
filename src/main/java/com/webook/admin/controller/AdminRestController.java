package com.webook.admin.controller;

import com.google.gson.reflect.TypeToken;
import com.webook.books.domain.Books;
import com.webook.books.domain.BooksKind;
import com.webook.books.service.BooksKindService;
import com.google.gson.Gson;
import com.webook.books.service.BooksService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangae1 on 2016-11-27.
 */
@RestController
public class AdminRestController {
    @Autowired
    private BooksKindService booksKindService;

    @Autowired
    private BooksService booksService;

    @RequestMapping(value="/books_kind_findAll", method= RequestMethod.POST)
    public String books_kind_findAll() {
        Gson gson = new Gson();
        return gson.toJson(booksKindService.findAll());
    }

    @RequestMapping(value="/books_findAll", method= RequestMethod.POST)
    public String books_findAll(@RequestParam("books_kind_cd")String books_kind_cd) {
        Gson gson = new Gson();
        return gson.toJson(booksService.findByBooksKindCd(books_kind_cd));
    }

    @RequestMapping(value="/books_save", method= RequestMethod.POST)
    public String books_save(HttpServletRequest request, @RequestParam("ins")String ins) {
        Gson gson = new Gson();
        Type typeOfList = new TypeToken<List<Books>>(){}.getType();
        List<Books> list = gson.fromJson(ins, typeOfList);
        for(Books book : list) {
            this.booksService.save(book);
        }
        return gson.toJson(booksService.findByBooksKindCd(list.get(0).getBooks_kind_cd()));
    }

    @RequestMapping(value="/books_save_enctype", method= RequestMethod.POST)
    public String books_save_enctype(MultipartHttpServletRequest request) {
        Gson gson = new Gson();
        List<MultipartFile> files = request.getFiles("file");
        String[] kind_cd = request.getParameterValues("books_kind_cd");
        String[] nm = request.getParameterValues("books_nm");
        String[] file_nm = request.getParameterValues("books_file_nm");
        for(int i=0; i<files.size(); i++) {
            Books books = new Books();
            books.setBooks_file_nm(file_nm[i]);
            books.setBooks_nm(nm[i]);
            books.setBooks_kind_cd(kind_cd[i]);
            books.setFile(files.get(i));
            this.booksService.save_enctype(books);
        }
        return gson.toJson(booksService.findByBooksKindCd(request.getParameter("books_kind_cd")));
    }

    @RequestMapping(value="/books_update", method= RequestMethod.POST)
    public String books_update(@RequestParam("upd")String upd) {
        Gson gson = new Gson();
        Type typeOfList = new TypeToken<List<Books>>(){}.getType();
        List<Books> list = gson.fromJson(upd, typeOfList);
        for(Books book : list) {
            this.booksService.update(book);
        }
        return gson.toJson(booksService.findByBooksKindCd(list.get(0).getBooks_kind_cd()));
    }

    @RequestMapping(value="/books_kind_save", method= RequestMethod.POST)
    public String books_kind_save(@RequestParam("ins")String ins) {
        Gson gson = new Gson();
        System.out.println(ins + "*************");
        Type typeOfList = new TypeToken<List<BooksKind>>(){}.getType();
        List<BooksKind> list = gson.fromJson(ins, typeOfList);
        for(BooksKind booksKind : list) {
            this.booksKindService.save(booksKind);
        }
        return gson.toJson(booksKindService.findAll());
    }

    @RequestMapping(value="/books_kind_update", method= RequestMethod.POST)
    public String books_kind_update(@RequestParam("upd")String upd) {
        Gson gson = new Gson();
        Type typeOfList = new TypeToken<List<BooksKind>>(){}.getType();
        List<BooksKind> list = gson.fromJson(upd, typeOfList);
        for(BooksKind booksKind : list) {
            this.booksKindService.update(booksKind);
        }
        return gson.toJson(booksKindService.findAll());
    }
}
