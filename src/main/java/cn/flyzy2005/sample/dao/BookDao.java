package cn.flyzy2005.sample.dao;


import cn.flyzy2005.dao.AbstractDao;
import cn.flyzy2005.sample.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * Created by Fly on 2017/4/21.
 */
@Repository
public class BookDao extends AbstractDao<Book>{
    public BookDao(){
        setTableName("book");
    }
}
