package ru.kinopoisk.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kinopoisk.api.models.Film;

public class JsonParser {
    public Film getCurrentFilm(JsonNode rootNode) throws JsonProcessingException {
        JsonNode dataNode = rootNode.path("data");
        return new ObjectMapper().treeToValue(dataNode, Film.class);
    }
}
