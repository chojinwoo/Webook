package com.webook.main.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webook.books.domain.Books;
import com.webook.books.domain.BooksKind;
import com.webook.books.domain.BooksLog;
import com.webook.books.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bangae1 on 2016-11-25.
 */
@Controller
public class MainController {
    @Autowired
    private BooksService booksService;

    @Autowired
    private BooksKindService booksKindService;

    @Autowired
    private BooksMarkService booksMarkService;

    @Autowired
    private BooksLogService booksLogService;

    @Autowired
    private BooksCategoryService booksCategoryService;

    @RequestMapping(value="/")
    public String index(Model model, Principal principal) {
        String id = "";
        try {
            if (principal.getName() != null) id = principal.getName();
            model.addAttribute("booksKinds", booksKindService.findAll());
            model.addAttribute("books", booksService.findAll());
            model.addAttribute("logs", booksLogService.findByid(id));
            model.addAttribute("categorys", this.booksCategoryService.findAll());
        }catch(Exception e) {
            System.out.println("login page");
        }
        return "index";
    }


    @RequestMapping(value="/bookView")
    public String bookView() {
        return "reader";
    }


    @RequestMapping(value="/logAnalysis")
    public String logAnalysis(Model model, Principal principal) {
        Gson gson = new Gson();
        List list = new LinkedList();
        List<BooksLog> logList = booksLogService.findAll(principal.getName());
        List<Books> booksList = booksService.findAll();
        list.add(logList.size());
        list.add((booksList.size() - logList.size()));
        model.addAttribute("chart", gson.toJson(list));
        model.addAttribute("logs", gson.toJson(logList));

        return "/analysis";
    }
}
