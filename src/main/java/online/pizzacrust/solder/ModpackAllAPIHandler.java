package online.pizzacrust.solder;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ModpackAllAPIHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpRequest) throws IOException {
        String response = new Gson().toJson(Main.MODPACK_DATABASE);
        httpRequest.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream body = httpRequest.getResponseBody();
        body.write(response.getBytes(StandardCharsets.UTF_8));
        body.close();
    }
}
