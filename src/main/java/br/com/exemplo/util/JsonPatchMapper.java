package br.com.exemplo.util;


import br.com.exemplo.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonPatchMapper {

    public static <T> T applyPatch(JsonPatch jsonPatch, T object) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = jsonPatch.apply(objectMapper.convertValue(object, JsonNode.class));
            return (T) objectMapper.treeToValue(jsonNode, object.getClass());

        } catch (JsonPatchException | JsonProcessingException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

}
