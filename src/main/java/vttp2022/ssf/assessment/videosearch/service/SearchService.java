package vttp2022.ssf.assessment.videosearch.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.assessment.videosearch.models.Game;

@Service
public class SearchService {

    private final static String URL = "https://api.rawg.io/api/games";

    @Value("${rawg.api.key}")
    private String apiKey;

    //set RAWG_API_KEY=919af7edcc7d401d8f0d6a5ee9f9286e

    public List<Game> search(String searchString, Integer count) {

        String searchUrl = UriComponentsBuilder
            .fromUriString(URL)
            .queryParam("search", searchString)
            .queryParam("page_size", count)
            .queryParam("key", apiKey)
            .toUriString();

        System.out.println(">>>>> URL: " + searchUrl);
        
        RequestEntity req = RequestEntity
            .get(searchUrl)
            .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader r = Json.createReader(is);
        JsonObject result = r.readObject();

        JsonArray resultsArr = result.getJsonArray("results");

        System.out.printf(">>>>> results = %s\n", resultsArr.toString());
        
        List<Game> list = new ArrayList<>();       
        for (int i=0 ; i < resultsArr.size() ; i++){
            Game game = new Game();
            String name = resultsArr.getJsonObject(i).getString("name");
            String released = resultsArr.getJsonObject(i).getString("released");
            Double rating = resultsArr.getJsonObject(i).getJsonNumber("rating").doubleValue();
            String backgroundImage = resultsArr.getJsonObject(i).getString("background_image");
            game.setName(name);
            game.setReleased(released);
            game.setRating(rating.floatValue());
            game.setBackgroundImage(backgroundImage);

            System.out.println(">>>>> game: " + game);

            list.add(game);
        }

        System.out.println(">>>>> list: " + list);

        return list;
    }
}
