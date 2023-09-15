package com.github.wellch4n.silver.bullet.json.fastjson;

import com.github.wellch4n.silver.bullet.json.api.JSONObject;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class FastJSONObject implements JSONObject {

    private final com.alibaba.fastjson2.JSONObject fJSONObject;

    public FastJSONObject(com.alibaba.fastjson2.JSONObject fJSONObject) {
        this.fJSONObject = fJSONObject;
    }

    @Override
    public Object get(String key) {
        return fJSONObject.get(key);
    }
}
