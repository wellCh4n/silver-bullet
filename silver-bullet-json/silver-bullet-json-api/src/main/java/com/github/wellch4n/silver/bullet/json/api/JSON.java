package com.github.wellch4n.silver.bullet.json.api;

import java.util.List;
import java.util.ServiceLoader;

/**
 * @author wellCh4n
 * @date 2023/9/9
 */
public class JSON {

    private static JSONAdapter jsonAdapter;

    static {
        ServiceLoader<JSONAdapter> adapters = ServiceLoader.load(JSONAdapter.class);
        for (JSONAdapter adapter : adapters) {
            if (adapter != null) {
                jsonAdapter = adapter;
            }
        }
        if (jsonAdapter == null) {
            throw new RuntimeException("No JSONAdapter found");
        }
    }

    public static JSONObject toObject(String str) {
        return jsonAdapter.toObject(str);
    }

    public static JSONArray toArray(String str) {
        return jsonAdapter.toArray(str);
    }

    public static String toString(JSONObject object) {
        return jsonAdapter.toString(object);
    }

    public static String toString(Object object) {
        return jsonAdapter.toString(object);
    }

    public static String toString(JSONArray array) {
        return jsonAdapter.toString(array);
    }

    public static String toString(List<?> list) {
        return jsonAdapter.toString(list);
    }
}
