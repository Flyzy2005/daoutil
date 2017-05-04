## 工具介绍
基于JdbcTemplate的通用操作数据库的帮助工具，借助阿里巴巴的开源工具fastjson实现实体的转json字符串以及params的解析。
## 用法
1. 建立与数据库表相对应的entity对象。

```
package cn.flyzy2005.sample.entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by Fly on 2017/5/4.
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;

    public Book() {
    }

    public Book(int id, String name, String author, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

```
- 成员变量的命名与数据库列名一致（大小写无所谓），例如列名ID可以对应ID、id、Id、iD。

- 重写toString()方法，将实体转为json格式的字符串，这里借助阿里巴巴的fastjson来实现这一功能。
2. 为每张表建立Dao操作类继承AbstractDao<T>，并设置表名。

```
package cn.flyzy2005.sample.dao;

import cn.flyzy2005.dao.AbstractDao;
import cn.flyzy2005.sample.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * Created by Fly on 2017/5/4.
 */
@Repository
public class BookDao extends AbstractDao<Book>{
    public BookDao(){
        setTableName("book");
    }
}
```
AbstracDao实现的数据库操作包括：

```
public interface IBaseDao<T> {
    /**
     * 根据id进行查询
     * @param id id
     * @return entity实例
     */
    T findById(Object id);

    /**
     *根据条件条件进行查询
     * @param jsonObject 查询条件{"name":"fly"}
     * @return 所有满足条件的entity实例
     */
    List<T> findByParams(JSONObject jsonObject);

    /**
     * 查询表中所有数据
     * @return 所有entity实例
     */
    List<T> findAll();

    /**
     * 根据sql语句进行查询
     * @param sql sql语句
     * @return 满足查询条件的entity实例
     */
    List<T> findBySql(String sql);

    /**
     * 添加一条记录
     * @param model entity实例
     * @param withId 是否需要插入Id
     * @return 是否插入成功
     */
    boolean add(T model, boolean withId);

    /**
     * 添加多条记录
     * @param modelList entity实例的List
     * @param withId 是否需要插入Id
     * @return 是否全部删除成功
     */
    boolean addList(List<T> modelList, boolean withId);

    /**
     * 根据Id删除一条记录
     * @param model entity实例
     * @return 是否删除成功
     */
    boolean delete(T model);

    /**
     * 根据Id删除一条记录
     * @param id Id
     * @return 是否删除成功
     */
    boolean deleteById(Object id);

    /**
     * 根据条件删除一条记录
     * @param jsonObject 删除条件
     * @return 是否删除成功
     */
    boolean deleteByParams(JSONObject jsonObject);

    /**
     * 修改一条记录，会根据传入的entity的Id进行匹配修改
     * @param model 修改的entity实例
     * @return 是否修改成功
     */
    boolean modify(T model);

    /**
     * 根据条件修改一条记录
     * @param model 修改的entity实例
     * @param jsonObject 条件
     * @return 是否修改成功
     */
    boolean modifyByParams(T model, JSONObject jsonObject);

    /**
     * 根据sql修改一条数据
     * @param sql sql语句
     * @return 是否修改成功
     */
    boolean modifyBySql(String sql);
}
```
3. 在spring的配置文件替换JdbcTmplate，使得queryForObject查询结果为0时，返回0而不是抛出异常。（也可以自己处理）

```
<!--<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">-->
	<!--<property name="dataSource" ref="dataSource"></property>-->
<!--</bean>-->
<bean id="jdbcTemplate" class="cn.flyzy2005.jdbc.DaoUtilJdbcTemplate">
	<property name="dataSource" ref="dataSource"/>
</bean>
```
## 引用：

```
<dependency>
    <groupId>cn.flyzy2005</groupId>
    <artifactId>daoutil</artifactId>
    <version>1.0.0</version>
</dependency>
```
