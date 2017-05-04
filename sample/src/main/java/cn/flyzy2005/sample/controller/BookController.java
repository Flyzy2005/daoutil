package cn.flyzy2005.sample.controller;

import cn.flyzy2005.sample.entity.Book;
import cn.flyzy2005.sample.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Fly on 2017/5/4.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService service;
    @RequestMapping("/find")
    public String getS(){
        List<Book> bookList = service.find();
        for(Book book : bookList)
            System.out.println("find " + book.getName() + ", id:" + book.getId());
        return "index";
    }
}
