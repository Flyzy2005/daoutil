package cn.flyzy2005.dao;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 * 定义基础的数据库操作接口
 */
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
     * @return
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
