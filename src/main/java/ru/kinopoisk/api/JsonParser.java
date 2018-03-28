package ru.kinopoisk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kinopoisk.api.models.*;

import java.io.IOException;

public class JsonParser {
    private int actualID;

    public Film getCurrentFilm(JsonNode rootNode) {
        Film result = null;
        try {
            JsonNode dataNode = rootNode.path("data");
            result = new ObjectMapper().treeToValue(dataNode, Film.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
