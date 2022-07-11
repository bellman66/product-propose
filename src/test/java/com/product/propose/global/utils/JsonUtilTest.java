package com.product.propose.global.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.product.propose.global.utils.json.JsonNodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.product.propose.global.utils.json.JsonNodeUtil.getNodeValueWithNull;

@SpringBootTest
public class JsonUtilTest {

    @Test
    void NodeFindTest() {
        JsonNodeFactory factory = JsonNodeFactory.instance;

        ObjectNode images = factory.objectNode();
        images.put("url", "url");

        JsonNode rootNode = factory.objectNode()
                        .set("image", images);

        final String originImgUrl = getNodeValueWithNull(JsonNodeUtil.ReturnType.STRING, rootNode,"image", "url");
    }
}
