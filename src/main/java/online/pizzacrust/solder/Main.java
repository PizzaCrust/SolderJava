package online.pizzacrust.solder;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String... args) throws Exception {
        System.out.println("Server is starting...");
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/api", new EntryAPIHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Stop command is now available to be used.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (true) {
            if (line != null) {
                if (line.equals("stop")) {
                    break;
                }
            }
        }
        System.out.println("Shutting down server...");
        server.stop(0);
    }

}
