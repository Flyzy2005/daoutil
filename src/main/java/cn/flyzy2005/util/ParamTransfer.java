package cn.flyzy2005.util;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 * 解析参数，拼接SQL语句
 */
public class ParamTransfer {
    public static Object[] paramsAnalyseSQL(JSONObject params) {

        if (params != null) {
            List<String> names = new ArrayList<String>();

            List<Object> values = new ArrayList<Object>();

            for (String key : params.keySet()) {

                names.add(key);

                values.add(params.get(key));

            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < names.size() - 1; i++) {

                sb.append(names.get(i) + "=? and ");

            }

            sb.append(names.get(names.size() - 1) + "=? ");

            return new Object[]{sb.toString(), values.toArray()};

        } else {

            return new Object[]{null, null};

        }

    }

    public static Object[] paramsAnalyseAddSQL(JSONObject params, boolean withId) {

        if (params != null) {

            List<String> names = new ArrayList<String>();

            List<Object> values = new ArrayList<Object>();

            for (String key : params.keySet()) {

                if (!withId && key.equals("id")) {
                    continue;
                }

                names.add(key);
                values.add(params.get(key));

            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < names.size() - 1; i++) {

                sb.append(names.get(i) + ", ");

            }

            sb.append(names.get(names.size() - 1) + " ");

            return new Object[]{sb.toString(), values.toArray()};

        } else {

            return new Object[]{null, null};

        }

    }

    public static Object[] paramsAnalyseUpdateSQL(JSONObject params) {
        if (params != null) {

            List<String> names = new ArrayList<String>();

            List<Object> values = new ArrayList<Object>();

            Object id = null;

            for (String key : params.keySet()) {

                if (!key.equals("id")) {

                    names.add(key);

                    values.add(params.get(key));

                } else {

                    id = params.get("id");

                }

            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < names.size() - 1; i++) {

                sb.append(names.get(i) + "=?, ");

            }

            sb.append(names.get(names.size() - 1) + "=? ");

            values.add(id);

            return new Object[]{sb.toString(), values.toArray()};

        } else {
            return new Object[]{null, null};
        }
    }

    public static Object[] paramsAnalyseUpdateSQLWithParams(JSONObject params, JSONObject conditions){
        if(params != null && conditions != null){
            List<String> names = new ArrayList<String>();

            List<Object> values = new ArrayList<Object>();

            for (String key : params.keySet()) {
                if (!key.equals("id")) {
                    names.add(key);

                    values.add(params.get(key));
                }
            }

            StringBuilder setBuilder = new StringBuilder();

            for (int i = 0; i < names.size() - 1; i++) {

                setBuilder.append(names.get(i) + "=?, ");

            }

            setBuilder.append(names.get(names.size() - 1) + "=? ");

            List<String> nameCondition = new ArrayList<String>();
            for(String key : conditions.keySet()){
                nameCondition.add(key);
                values.add(conditions.get(key));
            }
            StringBuilder conditionBuilder = new StringBuilder();
            conditionBuilder.append(nameCondition.get(0) + "=? ");
            for(int i = 1, size = nameCondition.size(); i < size; i++){
                conditionBuilder.append(" AND " + nameCondition.get(i) + "=? ");
            }

            return new Object[]{setBuilder.toString(), conditionBuilder.toString(), values.toArray()};
        }else{
            return new Object[]{null, null, null};
        }
    }
}
