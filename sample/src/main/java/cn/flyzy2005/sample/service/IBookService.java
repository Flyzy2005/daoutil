package cn.flyzy2005.sample.service;

import cn.flyzy2005.sample.entity.Book;

import java.util.List;

/**
 * Created by Fly on 2017/5/4.
 */
public interface IBookService {
    boolean add();
    List<Book> find();
    boolean delete();
    boolean modify();
}
