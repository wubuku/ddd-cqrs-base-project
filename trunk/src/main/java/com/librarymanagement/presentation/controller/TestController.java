package com.librarymanagement.presentation.controller;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 1/4/13
 * Time: 9:58 AM
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET,produces="application/json")
    @ResponseBody
    public Map first(){
        System.out.println("first");
        Map m = new HashMap();
        m.put("date",LocalDate.now());
        m.put("dateTime",DateTime.now());
        return m;
    }

}
