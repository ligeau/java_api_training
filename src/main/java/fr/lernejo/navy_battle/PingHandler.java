package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PingHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        final String answer = "OK";
        exchange.sendResponseHeaders(200,answer.length());
        try (OutputStream R = exchange.getResponseBody())
        {
            R.write(answer.getBytes());
        }
    }
}
