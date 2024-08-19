package com.github.wellch4n.silver.bullet.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.wellch4n.silver.bullet.json.api.JSONAdapter;
import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

import java.util.List;

/**
 * @author wellCh4n
 * @date 2023/9/10
 */
public class JacksonAdapter implements JSONAdapter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JSONObject toObject(String str) {
        try {
            JsonNode jsonNode = objectMapper.readTree(str);
            if (jsonNode instanceof ObjectNode) {
                return new JacksonObject((ObjectNode) jsonNode);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject partialToObject(String partialStr) {
        return null;
    }

    @Override
    public JSONArray toArray(String str) {
        try {
            JsonNode jsonNode = objectMapper.readTree(str);
            if (jsonNode instanceof ArrayNode) {
                return new JacksonArray((ArrayNode) jsonNode);
            }
            return null;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String toString(JSONObject object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String toString(JSONArray array) {
        try {
            return objectMapper.writeValueAsString(array);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String toString(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
