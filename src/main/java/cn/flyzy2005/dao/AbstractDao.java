package cn.flyzy2005.dao;


import cn.flyzy2005.util.ParamTransfer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 * 实现操作接口
 */
public abstract class AbstractDao<T> implements IBaseDao<T> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String tableName;

    public T findById(Object id) {
        T t = null;
        if (null != id) {
            String sql = "SELECT * FROM " + getTableName() + " WHERE ID = ?";
            t = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<T>(getTClass()),
                    id);
        }
        return t;
    }

    public List<T> findByParams(JSONObject jsonObject) {
        Object[] params = ParamTransfer.paramsAnalyseSQL(jsonObject);

        if (params[0] != null && params[1] != null) {
            String sql = " SELECT * FROM " + getTableName() + " WHERE " + params[0];
            return this.jdbcTemplate.query(sql,
                    (Object[]) params[1],
                    new BeanPropertyRowMapper<T>(getTClass()));
        } else {
            return new ArrayList<T>();
        }
    }

    public List<T> findAll() {
        String sql = " SELECT * FROM " + getTableName();
        return this.jdbcTemplate.query(sql,
                new Object[]{},
                new BeanPropertyRowMapper<T>(getTClass()));
    }

    public List<T> findBySql(String sql) {
        if (null != sql) {
            return this.jdbcTemplate.query(sql,
                    new Object[]{},
                    new BeanPropertyRowMapper<T>(getTClass()));
        } else {
            return new ArrayList<T>();
        }
    }

    public boolean add(T model, boolean withId) {
        if (model != null) {
            JSONObject jmodel = JSONObject.parseObject(model.toString());
            Object[] params = ParamTransfer.paramsAnalyseAddSQL(jmodel, withId);

            if (params[0] != null && params[1] != null) {
                int count = ((Object[]) params[1]).length;
                StringBuilder paramsb = new StringBuilder();

                for (int i = 0; i < count - 1; i++) {
                    paramsb.append("?,");
                }
                paramsb.append("?");

                String sql = "INSERT INTO " + getTableName() + "(" + params[0] + ") VALUES(" + paramsb + ")";

                int result = this.jdbcTemplate.update(sql, (Object[]) params[1]);

                if (result >= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addList(List<T> modelList, boolean withId) {
        if (modelList.size() > 0) {
            JSONObject jmodel = JSONObject.parseObject(modelList.get(0).toString());
            Object[] params = ParamTransfer.paramsAnalyseAddSQL(jmodel, withId);
            String sql = "";
            if (params[0] != null && params[1] != null) {
                int count = ((Object[]) params[1]).length;
                StringBuilder paramsb = new StringBuilder();

                for (int i = 0; i < count - 1; i++) {
                    paramsb.append("?,");
                }
                paramsb.append("?");

                sql = "INSERT INTO " + getTableName() + "(" + params[0] + ") VALUES(" + paramsb + ")";
            }
            List<Object[]> batchArgs = new ArrayList<Object[]>();
            for (T model : modelList) {
                JSONObject jmodelTmp = JSONObject.parseObject(model.toString());
                Object[] paramsTmp = ParamTransfer.paramsAnalyseAddSQL(jmodelTmp, withId);
                batchArgs.add((Object[]) paramsTmp[1]);
            }
            if ("".equals(sql))
                return false;
            int[] countArray = jdbcTemplate.batchUpdate(sql, batchArgs);
            for (int i : countArray) {
                if (0 == i)
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean delete(T model) {
        if (model != null) {
            JSONObject jmodel = JSONObject.parseObject(model.toString());
            return deleteById(jmodel.get("id"));
        } else {
            return false;
        }
    }

    public boolean deleteById(Object id) {
        if (id != null) {
            String sql = "DELETE FROM " + getTableName() + " WHERE id=?";
            int num = this.jdbcTemplate.update(sql, new Object[]{id});
            return num > 0;
        } else {
            return false;
        }
    }

    public boolean deleteByParams(JSONObject jsonObject) {
        Object[] params = ParamTransfer.paramsAnalyseSQL(jsonObject);

        if (params[0] != null && params[1] != null) {
            String sql = " DELETE FROM " + getTableName() + " WHERE " + params[0];
            int num = this.jdbcTemplate.update(sql,
                    (Object[]) params[1]);
            return num > 0;
        } else {
            return false;
        }
    }

    public boolean modify(T model) {
        if (model != null) {
            JSONObject jmodel = JSONObject.parseObject(model.toString());
            Object[] params = ParamTransfer.paramsAnalyseUpdateSQL(jmodel);

            if (params[0] != null && params[1] != null) {
                String sql = "UPDATE " + getTableName() + " SET " + params[0] + " WHERE id=?";
                int num = this.jdbcTemplate.update(sql, (Object[]) params[1]);
                if (num > 0) return true;
            }
        }
        return false;
    }

    public boolean modifyByParams(T model, JSONObject jsonObject) {
        if (model != null) {
            JSONObject jmodel = JSONObject.parseObject(model.toString());
            Object[] params = ParamTransfer.paramsAnalyseUpdateSQLWithParams(jmodel, jsonObject);

            if (params[0] != null && params[1] != null) {
                String sql = "UPDATE " + getTableName() + " SET " + params[0] + " WHERE " + params[1];
                int num = this.jdbcTemplate.update(sql,
                        (Object[]) params[2]);
                return num > 0;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean modifyBySql(String sql) {
        if (null != sql) {
            int num = this.jdbcTemplate.update(sql);
            if (num > 0) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    private String getTableName() {
        return tableName;
    }

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
