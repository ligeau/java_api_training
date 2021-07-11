package fr.lernejo.navy_battle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
    public final String id;
    public final String url;
    public final String message;

     Message(@JsonProperty("id") String id,@JsonProperty("url") String url,@JsonProperty("message") String message) {
        this.id = id;
        this.url = url;
        this.message = message;

     }

}
