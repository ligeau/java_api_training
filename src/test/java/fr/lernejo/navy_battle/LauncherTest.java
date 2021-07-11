package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LauncherTest {

    @Test
    public  void startServeur() throws IOException, InterruptedException {
        var s = new MyServeur(6666);
        var c = s.staringServeur(6666);
        var client = HttpClient.newHttpClient();
        var r = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:6666/ping"))
            .GET()
            .build();
        var myResponce = client.send(r, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(myResponce.statusCode(), 200);
        Assertions.assertEquals(myResponce.body(), "OK");
        c.stop(0);
    }
    @Test
    public  void ServerPost() throws IOException, InterruptedException {
        var s = new MyServeur(6666);
        var c = s.staringServeur(6666);
        var client = HttpClient.newHttpClient();
        var r = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:6666/api/game/start"))
            .GET()
            .build();
        var myResponce = client.send(r, HttpResponse.BodyHandlers.ofString());
        c.stop(0);
    }

}
