package cn.flyzy2005.sample.service.impl;


import cn.flyzy2005.sample.dao.BookDao;
import cn.flyzy2005.sample.entity.Book;
import cn.flyzy2005.sample.service.IBookService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 */
@Service
public class BookService implements IBookService {
    @Autowired
    private BookDao bookDao;

    public boolean add() {
        Book book = new Book(1000, "程序设计", "fly", "whu");
        bookDao.add(book, true);
        List<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book(1001, "数据结构","fly1", "whu1");
        Book book2 = new Book(1002, "编译原理","fly", "whu");
        Book book3 = new Book(1003, "算法导论","fly1", "whu2");
        Book book4 = new Book(1004, "Java编程思想","fly2", "whu3");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookDao.addList(bookList, true);
        return true;
    }

    public List<Book> find() {
        Book book1 = bookDao.findById(1000);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author","fly1");
        List<Book> bookList1 = bookDao.findByParams(jsonObject);
        List<Book> bookList2 = bookDao.findAll();

        List<Book> result = new ArrayList<Book>();
        result.add(book1);
        result.addAll(bookList1);
        result.addAll(bookList2);
        return result;
    }

    public boolean delete() {
        Book book1 = bookDao.findById(1000);
        bookDao.delete(book1);
        bookDao.deleteById(1001);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author","fly");
        bookDao.deleteByParams(jsonObject);
        return true;
    }

    public boolean modify() {
        Book book1 = bookDao.findById(1004);
//        book1.setPublisher("whuTest");
//        bookDao.modify(book1);

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", 1000);
//        book1.setId(1006);
//        book1.setPublisher("hah");
//        book1.setAuthor("mm");
//        bookDao.modifyByParams(book1, jsonObject);
//
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author","fly1");
        bookDao.modifyByParams(book1, jsonObject);
        return false;
    }
}
