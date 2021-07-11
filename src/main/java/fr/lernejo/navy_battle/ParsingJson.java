package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.fasterxml.*;

import java.io.IOException;
import java.io.OutputStream;

public class ParsingJson implements HttpHandler {

    final private Option<Message> value = new Option<Message>();

    @Override
    public  void handle(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            value.set(mapper.readValue(exchange.getResponseBody().toString(), Message.class));
        } catch (JsonProcessingException e) {
            final String answer = "Bad Reques";
            exchange.sendResponseHeaders(400,answer.length());
            try (OutputStream R = exchange.getResponseBody()) { R.write(answer.getBytes()); }
        }
        final String answer = "Accepted";
        exchange.sendResponseHeaders(202,answer.length());
        try (OutputStream R = exchange.getResponseBody()) {
            R.write(answer.getBytes()); }
    }
}
