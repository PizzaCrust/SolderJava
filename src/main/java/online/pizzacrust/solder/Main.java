package online.pizzacrust.solder;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Main {

    public static SolderConfigInfo CONFIGURATION;
    public static ModpacksInfo MODPACK_DATABASE;

    public static void main(String... args) throws Exception {
        int port = Integer.parseInt(args[0]);
        File configFile = new File(System.getProperty("user.dir"), "config.json");
        File mirrorFile = new File(System.getProperty("user.dir"), "mirror");
        if (!configFile.exists()) {
            Files.write(configFile.toPath(), new Gson().toJson(SolderConfigInfo.Default.get())
                            .getBytes(Charset.defaultCharset()), StandardOpenOption.CREATE);
            System.out.println("Config created, aborting launch.");
            return;
        }
        if (!mirrorFile.exists()) {
            mirrorFile.mkdir();
        }
        CONFIGURATION = new Gson().fromJson(new FileReader(configFile), SolderConfigInfo.class);
        MODPACK_DATABASE = new ModpacksInfo(CONFIGURATION.registeredModpacks, CONFIGURATION
                .rootURL + "/mirror/");
        System.out.println("Server is starting...");
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api", new EntryAPIHandler());
        server.createContext("/api/modpack", new ModpackAPIHandler());
        server.createContext("/test", new TestHandler());
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
