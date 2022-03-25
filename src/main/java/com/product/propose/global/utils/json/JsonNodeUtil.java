package com.product.propose.global.utils.json;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public class JsonNodeUtil {

    public static <T> T getNodeValueWithNull(Class<T> returnType, JsonNode target, String ...fieldNames) {
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

    private static Object getTypeValue(JsonNode aTarget, Class<?> aClass) {
        // Assert - Json Node
        if (isNullOrEmpty(aTarget)) return null;

        switch (aClass.getSimpleName()) {
            case "String":
                return aTarget.asText();
            case "Long":
                return aTarget.asLong();
//            case "LocalDateTime":
//                Instant time = new Date(searchWithNull(rootNode, "time").asLong(0)).toInstant();
//                final LocalDateTime registerDateTime = LocalDateTime.ofInstant(time, ZoneId.of("Asia/Seoul"));
//                return
            default:
                break;
        }
        return null;
    }

    private static JsonNode searchWithNull(JsonNode target, String fieldName) {
        return target.has(fieldName) ? target.get(fieldName) : null;
    }

    private static boolean isNullOrEmpty(JsonNode target) {
        return Objects.isNull(target) || target.isNull() || target.isEmpty();
    }

    private static String getTextWithBlank(JsonNode target) {
        return target.asText();
    }
}
