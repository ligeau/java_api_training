package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServeur {

    public static void staringServeur(int p) {
        try {
            HttpServer s = HttpServer.create(new InetSocketAddress(p), 0);
            s.setExecutor(Executors.newSingleThreadExecutor());
            s.createContext("/ping", new PingHandler());
            s.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
