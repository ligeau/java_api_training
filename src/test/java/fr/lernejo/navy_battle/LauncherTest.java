package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Array;

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

    @Test
    public  void startLunchertes() throws IOException, InterruptedException {
        String[] a = new String[1];
        a[0] = "9877";
        var c =  Launcher.main2(a);
        var client = HttpClient.newHttpClient();
        var r = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9877/ping"))
            .GET()
            .build();
        var myResponce = client.send(r, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(myResponce.statusCode(), 200);
        Assertions.assertEquals(myResponce.body(), "OK");
        c.stop(0);
    }
    @Test
    public  void startLunchertespost() throws IOException, InterruptedException {
        String[] a = new String[1];
        a[0] = "9877";
        int  myPort = 66666;
        var c =  Launcher.main2(a);
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9877/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();
        c.stop(0);
    }
    @Test
    public  void startLunchertespost2() throws IOException, InterruptedException {
        String[] a = new String[2];
        String[] b = new String[1];
        b[0] = "9876";

        var d =  Launcher.main2(b);
        a[0] = "9877";
        a[1]= "http://localhost:9876";
        int  myPort = 66666;
        var c =  Launcher.main2(a);
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9877/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();
        c.stop(0);
        d.stop(0);
    }



}
