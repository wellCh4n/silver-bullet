package com.github.wellch4n.silver.bullet.json.fastjson;

import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class FastJSONArray implements JSONArray {

    private final com.alibaba.fastjson2.JSONArray fJSONArray;

    public FastJSONArray(com.alibaba.fastjson2.JSONArray fJSONArray) {
        this.fJSONArray = fJSONArray;
    }

    @Override
    public JSONObject get(int index) {
        com.alibaba.fastjson2.JSONObject fJSONObject = fJSONArray.getJSONObject(index);
        return new FastJSONObject(fJSONObject);
    }

    @Override
    public int size() {
        return fJSONArray.size();
    }
}
