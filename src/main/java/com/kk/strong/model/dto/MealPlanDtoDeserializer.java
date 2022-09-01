package com.kk.strong.model.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Iterator;

public class MealPlanDtoDeserializer extends JsonDeserializer<MealPlanDto> {

    @Override
    public MealPlanDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> mealsNodeIterator = node.get("meals").elements();
        JsonNode nutrientsNode = node.get("nutrients");

        return MealPlanDto
                .builder()
                .breakfast(mealsNodeIterator.next().get("title").asText())
                .lunch(mealsNodeIterator.next().get("title").asText())
                .dinner(mealsNodeIterator.next().get("title").asText())
                .calories(nutrientsNode.get("calories").asDouble())
                .protein(nutrientsNode.get("protein").asDouble())
                .fat(nutrientsNode.get("fat").asDouble())
                .carbohydrates(nutrientsNode.get("carbohydrates").asDouble())
                .build();
    }
}
