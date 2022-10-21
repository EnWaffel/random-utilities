package de.enwaffel.gc;

import com.sun.net.httpserver.HttpServer;
import de.enwaffel.randomutils.net.NetServer;
import de.enwaffel.randomutils.sql.MySQL;
import de.enwaffel.randomutils.sql.SQL;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceServer {

    private static MySQL sql;
    private static NetServer server;

    public static void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutting down...");
            if (server != null) server.stop();
        }));
        long start = System.currentTimeMillis();
        System.out.println("initializing...");
        System.out.println("connecting to database...");
        try {
            sql = SQL.connect(MySQL.class, "localhost", "guardiencraft", "admin", "qZD=rlL?sMU]lyW<oD@a");
            System.out.println("connected to database.");
        } catch (Exception e) {
            System.out.println("failed to connect to database; exiting in 10 seconds...");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(-1);
                    cancel();
                }
            }, 10000, 1);
            return;
        }
        System.out.println("starting http server...");
        try {
            server = new NetServer();
            server.start(80);
            System.out.println("server successfully started.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to start server; exiting in 10 seconds...");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(-1);
                    cancel();
                }
            }, 10000, 1);
            return;
        }
        System.out.println("initializing handlers...");
        initHandlers();
        System.out.println("server started in " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void initHandlers() {

    }

    public static void main(String[] args) {
        init();
    }

}
