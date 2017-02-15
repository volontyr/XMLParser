package com.volontyr.controller;

import com.volontyr.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by volontyr on 15.02.17.
 */
@Controller
public class MainController {

    @Autowired
    private ParserService xmlParser;

    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String getParsePage() {
        return "parse";
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public String parseXML(@RequestParam("nodeName") String nodeName, @RequestParam("cnt") int cnt, Model model) {
        List<String> nodes = xmlParser.parse(nodeName, cnt);
        model.addAttribute("nodes", nodes);

        return "parse";
    }
}
