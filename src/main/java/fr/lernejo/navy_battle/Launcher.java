package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            System.out.println("try lunch server " + port);
            var serv  =  new MyServeur(port);
            serv.staringServeur(port);

        } catch (Exception e)
        {
            System.out.println("something goes wrong");
        }
    }


}
