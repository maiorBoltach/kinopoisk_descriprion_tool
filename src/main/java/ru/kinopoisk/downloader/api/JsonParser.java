package ru.kinopoisk.downloader.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kinopoisk.downloader.data.Film;

class JsonParser {
    static Film getCurrentFilm(JsonNode rootNode) throws JsonProcessingException {
        JsonNode dataNode = rootNode.path("data");
        return new ObjectMapper().treeToValue(dataNode, Film.class);
    }
}
