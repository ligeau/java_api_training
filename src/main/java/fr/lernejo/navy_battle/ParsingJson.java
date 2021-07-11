package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ParsingJson implements HttpHandler {

    final private Option<Message> value = new Option<Message>();

    @Override
    public  void handle(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            value.set(mapper.readValue(new InputStreamReader( exchange.getRequestBody()), Message.class));
        } catch (JsonProcessingException e) {
            senderr(exchange);
            return;
        }
        if (!exchange.getRequestMethod().equals("POST") || value.get().id == null ||  value.get().message == null || value.get().url == null) {
            senderr(exchange);
            return;
        }
        final String answer = "Accepted";
        exchange.sendResponseHeaders(202,answer.length());
        try (OutputStream R = exchange.getResponseBody()) {
            R.write(answer.getBytes()); } }

    private void senderr(HttpExchange exchange) throws IOException {
        final String answer = "Bad Request";
        exchange.sendResponseHeaders(400,answer.length());
        try (OutputStream R = exchange.getResponseBody()) { R.write(answer.getBytes()); }
    }
}
