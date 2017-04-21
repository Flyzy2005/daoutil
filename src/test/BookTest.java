import cn.flyzy2005.sample.entity.Book;
import cn.flyzy2005.sample.service.IBookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 */
public class BookTest extends BaseTest {
    @Autowired
    IBookService bookService;

    @Test
    public void add(){
        bookService.add();
    }

    @Test
    public void find(){
        List<Book> bookList = bookService.find();
        for(Book book : bookList)
            System.out.println("find " + book.getName() + ", id:" + book.getId());
    }

    @Test
    public void delete(){
        bookService.delete();
    }

    @Test
    public void modify(){
        bookService.modify();
    }
}
