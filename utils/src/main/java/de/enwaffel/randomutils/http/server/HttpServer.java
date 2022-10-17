package de.enwaffel.randomutils.http.server;

import sun.net.httpserver.HttpServerImpl;

import java.net.ServerSocket;

public class HttpServer {

    private ServerSocket server;

    public HttpServer start(int port) {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            error("Failed to start HttpServer");
        }
        return this;
    }


    // util

    private void info(String text) {
        System.err.println("[INFO] " + text);
    }

    private void warning(String text) {
        System.err.println("[WARNING] " + text);
    }

    private void error(String text) {
        System.err.println("[ERROR] " + text);
    }

}
