package com.github.wellch4n.silver.bullet.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class JacksonArray implements JSONArray {

    private final ArrayNode arrayNode;

    public JacksonArray(ArrayNode arrayNode) {
        this.arrayNode = arrayNode;
    }

    @Override
    public JSONObject get(int index) {
        JsonNode jsonNode = arrayNode.get(index);
        return new JacksonObject((ObjectNode) jsonNode);
    }

    @Override
    public int size() {
        return arrayNode.size();
    }
}
