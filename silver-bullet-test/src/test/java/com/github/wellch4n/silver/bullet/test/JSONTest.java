package com.github.wellch4n.silver.bullet.test;

import com.github.wellch4n.silver.bullet.json.api.JSON;
import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class JSONTest {

    @Test
    public void test1() {
        JSONArray array = JSON.toArray("[{\"1\":\"2\"}]");
        Assertions.assertEquals(1, array.size());
    }
}
