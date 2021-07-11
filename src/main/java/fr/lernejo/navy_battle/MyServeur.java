package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServeur {
    final  String id;
    final  String url;
    final  Option<Message> value = new Option<Message>();
    final  String yaurl;

    public MyServeur(int p) {
        this.id = java.util.UUID.randomUUID().toString();
        this.url = "localhost:" + p;
        this.yaurl = null;
    }
    public MyServeur(int p, String str) {
        this.id = java.util.UUID.randomUUID().toString();
        this.url = "localhost:" + p;
        this.yaurl = str;
    }

    public HttpServer staringServeur(int p) throws IOException {
            HttpServer s = HttpServer.create(new InetSocketAddress(p), 0);
            s.setExecutor(Executors.newSingleThreadExecutor());
            s.createContext("/ping", new PingHandler());
            s.createContext("/api/game/start", this::handle);
            s.start();
            return s;
    }

    public  void handle(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            value.set(mapper.readValue(new InputStreamReader(exchange.getRequestBody()), Message.class));
        } catch (JsonProcessingException e) {
            senderr(exchange);
            return;
        }
        if (!exchange.getRequestMethod().equals("POST") || value.get().id == null || value.get().message == null || value.get().url == null) {
            senderr(exchange);
            return;
        }
        GenreMessage(exchange);
    }

    private  void senderr(HttpExchange exchange) throws IOException {
        final String answer = "Bad Request";
        exchange.sendResponseHeaders(400,answer.length());
        try (OutputStream R = exchange.getResponseBody()) { R.write(answer.getBytes()); }
    }

    private  void GenreMessage(HttpExchange exchange) throws IOException {
        final String answer = "{\"id\":\"" + this.id +"\", \"url\":\"" + this.url + "\", \"message\":\"I want 2 die\"}";
        exchange.sendResponseHeaders(202,answer.length());
        try (OutputStream R = exchange.getResponseBody()) {
            R.write(answer.getBytes());
        }
    }

}
