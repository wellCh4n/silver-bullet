package com.github.wellch4n.silver.bullet.json.api;

import java.util.List;

/**
 * @author wellCh4n
 * @date 2023/9/9
 */
public interface JSONAdapter {

    JSONObject toObject(String str);

    JSONArray toArray(String str);

    String toString(JSONObject object);

    String toString(Object object);

    String toString(JSONArray array);

    String toString(List<?> list);
}
