package com.webook.main.controller;

import com.google.gson.Gson;
import com.webook.books.domain.BooksLog;
import com.webook.books.domain.BooksMark;
import com.webook.books.service.BooksKindService;
import com.webook.books.service.BooksLogService;
import com.webook.books.service.BooksMarkService;
import com.webook.books.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by bangae1 on 2016-11-26.
 */
@RestController
public class MainRestController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private BooksKindService booksKindService;

    @Autowired
    private BooksMarkService booksMarkService;

    @Autowired
    private BooksLogService booksLogService;

    @RequestMapping(value="/bookMark", method = RequestMethod.POST)
    public String bookMark(@ModelAttribute("command")BooksMark booksMark, Principal principal) {
        booksMark.setId(principal.getName());
        booksMarkService.save(booksMark);
        Gson gson = new Gson();
        return gson.toJson(booksMarkService.findByBooks_seq(booksMark.getId(), booksMark.getBooks_seq()));
    }

    @RequestMapping(value="/bookLog", method = RequestMethod.POST)
    public String bookLog(@ModelAttribute("command")BooksLog booksLog, Principal principal) {
        booksLog.setId(principal.getName());
        booksLogService.save(booksLog);
        Gson gson = new Gson();
        return gson.toJson(booksLogService.findByid(principal.getName()));
    }

    @RequestMapping(value="/findByBooks_seq/{seq}", method=RequestMethod.POST)
    public String findByBooks_seq(@PathVariable("seq")int seq, Principal principal) {
        Gson gson = new Gson();
        return gson.toJson(booksMarkService.findByBooks_seq(principal.getName(), seq));
    }

    @RequestMapping(value="/findUrlByBookSeq/{seq}", method = RequestMethod.POST)
    public String findByBookSeq(@PathVariable("seq")int seq, Principal principal) {
        BooksLog booksLog = new BooksLog();
        booksLog.setBooks_seq(seq);
        booksLog.setId(principal.getName());
        booksLogService.save(booksLog);
        return booksService.findByBookSeq(seq).getBooks_url();
    }

    @RequestMapping(value="/bookMarkDel", method = RequestMethod.POST)
    public String bookMarkDel(@ModelAttribute("command")BooksMark booksMark, Principal principal) {
        booksMark.setId(principal.getName());
        this.booksMarkService.delete(booksMark);
        Gson gson = new Gson();
        return gson.toJson(booksMarkService.findByBooks_seq(booksMark.getId(), booksMark.getBooks_seq()));
    }

    @RequestMapping(value="/bookLogFindById", method = RequestMethod.POST)
    public String bookLogFindById(Principal principal) {
        Gson gson = new Gson();
        return gson.toJson(booksLogService.findByid(principal.getName()));
    }
}
