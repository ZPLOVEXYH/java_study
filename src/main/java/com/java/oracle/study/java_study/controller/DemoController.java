package com.java.oracle.study.java_study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
    public String doTest() {
        return "test";
    }
}
