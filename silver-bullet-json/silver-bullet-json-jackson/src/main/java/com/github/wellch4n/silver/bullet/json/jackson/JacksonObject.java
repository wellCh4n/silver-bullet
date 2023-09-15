package com.github.wellch4n.silver.bullet.json.jackson;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class JacksonObject implements JSONObject {

    private final ObjectNode objectNode;

    public JacksonObject(ObjectNode objectNode) {
        this.objectNode = objectNode;
    }

    @Override
    public Object get(String key) {
        return objectNode.get(key);
    }
}
