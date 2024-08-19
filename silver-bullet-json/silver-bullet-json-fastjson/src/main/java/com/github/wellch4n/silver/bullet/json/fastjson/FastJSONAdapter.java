package com.github.wellch4n.silver.bullet.json.fastjson;

import com.alibaba.fastjson2.JSONException;
import com.github.wellch4n.silver.bullet.json.api.JSONAdapter;
import com.github.wellch4n.silver.bullet.json.api.JSONArray;
import com.github.wellch4n.silver.bullet.json.api.JSONObject;

import java.util.ArrayDeque;
import java.util.Deque;
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
    public JSONObject partialToObject(String partialStr) {
        try {
            com.alibaba.fastjson2.JSONObject fJsonObject = com.alibaba.fastjson2.JSONObject.parseObject(partialStr);
            return new FastJSONObject(fJsonObject);
        } catch (JSONException ignored) {}

        StringBuilder newChars = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        boolean isInsideString = false;
        boolean escaped = false;

        for (char ch : partialStr.toCharArray()) {
            if (isInsideString) {
                if (ch == '"' && !escaped) {
                    isInsideString = false;
                } else if (ch == '\n' && !escaped) {
                    newChars.append("\\n");  // Replace the newline character with the escape sequence.
                    continue;
                } else if (ch == '\\') {
                    escaped = !escaped;
                } else {
                    escaped = false;
                }
            } else {
                if (ch == '"') {
                    isInsideString = true;
                } else if (ch == '{') {
                    stack.push('}');
                } else if (ch == '[') {
                    stack.push(']');
                } else if (ch == '}' || ch == ']') {
                    if (!stack.isEmpty() && stack.peek() == ch) {
                        stack.pop();
                    } else {
                        return null;
                    }
                }
            }

            newChars.append(ch);
        }

        if (isInsideString) {
            newChars.append('"');
        }

        StringBuilder closingChars = new StringBuilder();
        while (!stack.isEmpty()) {
            closingChars.append(stack.pop());
        }

        while (newChars.length() > 0) {
            try {
                com.alibaba.fastjson2.JSONObject fJsonObject = com.alibaba.fastjson2.JSONObject.parseObject(newChars + closingChars.toString());
                return new FastJSONObject(fJsonObject);
            } catch (JSONException e) {
                newChars.setLength(newChars.length() - 1);
            }
        }

        throw new JSONException("Failed to parse JSON string.");
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
