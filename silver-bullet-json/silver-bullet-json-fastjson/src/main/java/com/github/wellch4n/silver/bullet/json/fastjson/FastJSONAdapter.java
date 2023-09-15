package com.github.wellch4n.silver.bullet.json.fastjson;

import com.github.wellch4n.silver.bullet.json.api.JSONAdapter;
import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

import java.util.List;

/**
 * @author wellCh4n
 * @date 2023/9/9
 */
public class FastJSONAdapter implements JSONAdapter {

    @Override
    public JSONObject toObject(String str) {
        com.alibaba.fastjson2.JSONObject fJSONObject = com.alibaba.fastjson2.JSONObject.parseObject(str);
        return new FastJSONObject(fJSONObject);
    }

    @Override
    public JSONArray toArray(String str) {
        com.alibaba.fastjson2.JSONArray fJSONArray = com.alibaba.fastjson2.JSONArray.parseArray(str);
        return new FastJSONArray(fJSONArray);
    }

    @Override
    public String toString(JSONObject object) {
        return com.alibaba.fastjson2.JSONObject.toJSONString(object);
    }

    @Override
    public String toString(Object object) {
        return com.alibaba.fastjson2.JSONObject.toJSONString(object);
    }

    @Override
    public String toString(JSONArray array) {
        return com.alibaba.fastjson2.JSONArray.toJSONString(array);
    }

    @Override
    public String toString(List<?> list) {
        return com.alibaba.fastjson2.JSONArray.toJSONString(list);
    }
}
