package demo.mathapp.service.chatgpt;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TextCompletionRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<>();
    @JsonProperty("response_format")
    private ObjectNode responseFormat;

    public TextCompletionRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
        ObjectMapper objectMapper = new ObjectMapper();
        responseFormat = objectMapper.createObjectNode();
        responseFormat.put("type", "json_schema");

        ObjectNode jsonSchema = objectMapper.createObjectNode();
        jsonSchema.put("name", "math_reasoning");
        ObjectNode schema = objectMapper.createObjectNode();
        schema.put("type", "object");

// Definiowanie properties
        ObjectNode properties = objectMapper.createObjectNode();

        ObjectNode tasks = objectMapper.createObjectNode();
        tasks.put("type", "array");

        ObjectNode taskItems = objectMapper.createObjectNode();
        taskItems.put("type", "object");

// Definiowanie task properties
        ObjectNode taskProperties = objectMapper.createObjectNode();
        taskProperties.putObject("content").put("type", "string");
        taskProperties.putObject("answer").put("type", "string");

        ObjectNode hints = objectMapper.createObjectNode();
        hints.put("type", "array");

        ObjectNode hintItems = objectMapper.createObjectNode();
        hintItems.put("type", "object");
        hintItems.putObject("properties").putObject("content").put("type", "string");
        hintItems.putArray("required").add("content");
        hintItems.put("additionalProperties", false);

        hints.set("items", hintItems);
        taskProperties.set("hints", hints);

        taskItems.set("properties", taskProperties);
        taskItems.putArray("required").add("content").add("answer").add("hints");
        taskItems.put("additionalProperties", false);

        tasks.set("items", taskItems);
        properties.set("tasks", tasks);

        schema.set("properties", properties);
        schema.putArray("required").add("tasks");
        schema.put("additionalProperties", false);

        jsonSchema.set("schema", schema);
        jsonSchema.put("strict", true);

        responseFormat.set("json_schema", jsonSchema);
    }
}
