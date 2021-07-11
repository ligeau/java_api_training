package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            System.out.println("try lunch server " + port);
            MyServeur serv  =  null;
            if (args.length == 1)
                serv = new MyServeur(port);
            else {
                serv = new MyServeur(port,args[1]);
            }
            serv.staringServeur(port);
            if (args.length != 1)
            {
                sendPostRequest(args[1], serv);
            }


        } catch (Exception e)
        {
            System.out.println("something goes wrong");
        }
    }
    public static void sendPostRequest(String adversaryUrl, MyServeur serv ) throws IOException, InterruptedException {
        java.net.http.HttpClient client = HttpClient.newHttpClient();

        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\""+ serv.id + "\", \"url\":\"" + serv.url + "\", \"message\":\"why are we still here ?\"}"))
            .build();
        var response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
    }

}
