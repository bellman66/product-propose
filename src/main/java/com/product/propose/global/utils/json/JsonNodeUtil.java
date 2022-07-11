package com.product.propose.global.utils.json;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public class JsonNodeUtil {

    public enum ReturnType {
        STRING, LONG, BOOLEAN
    }

    public static <T> T getNodeValueWithNull(ReturnType returnType, JsonNode target, String ...fieldNames) {
        JsonNode jsonNode = searchWithNull(target, fieldNames);
        return (T) getTypeValue(jsonNode, returnType);
    }

    private static JsonNode searchWithNull(JsonNode target, String ...fieldNames) {
        // Node Null Check
        if (Objects.isNull(target)) return null;

        JsonNode result = target;
        for (int i=0; i < fieldNames.length; i++) {
            if (Objects.isNull(result)) break;
            result = searchWithNull(result, fieldNames[i]);
        }

        return result;
    }

    private static Object getTypeValue(JsonNode aTarget, ReturnType returnType) {
        // Assert - Json Node
        if (isNull(aTarget)) return null;

        switch (returnType) {
            case STRING:
                return aTarget.asText();
            case LONG:
                return aTarget.asLong();
            case BOOLEAN :
                return aTarget.asBoolean(false);
            default:
                break;
        }
        return null;
    }

    private static JsonNode searchWithNull(JsonNode target, String fieldName) {
        return target.has(fieldName) ? target.get(fieldName) : null;
    }

    private static boolean isNull(JsonNode target) {
        return Objects.isNull(target) || target.isNull();
    }
}
