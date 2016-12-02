package com.webook.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bangae1 on 2016-11-27.
 */
@Controller
public class AdminController {
    @RequestMapping(value="/admin")
    public String admin() {
        return "admin/index";
    }
}
