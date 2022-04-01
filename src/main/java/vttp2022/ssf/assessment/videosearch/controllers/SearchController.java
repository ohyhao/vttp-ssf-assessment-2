package vttp2022.ssf.assessment.videosearch.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.assessment.videosearch.models.Game;
import vttp2022.ssf.assessment.videosearch.service.SearchService;

@Controller
@RequestMapping
public class SearchController {

     @Autowired
     private SearchService searchSvc;

     @GetMapping(path = "/search")
     public String searchGame(
         @RequestParam(required = true) String searchString,
         @RequestParam(defaultValue = "10") Integer count,
         Model model) {

          model.addAttribute("searchString", searchString);
          model.addAttribute("count", count);

          List<Game> resultList = searchSvc.search(searchString, count);
          model.addAttribute("results", resultList);

          System.out.printf(">>>>> resultList: " + resultList);

          if (resultList.isEmpty()) {
               return "error";
          } else {
               return "search";
          }  
     }
}
