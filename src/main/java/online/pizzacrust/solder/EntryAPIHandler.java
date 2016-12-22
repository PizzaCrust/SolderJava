package online.pizzacrust.solder;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class EntryAPIHandler implements HttpHandler {

    public void handle(HttpExchange httpRequest) throws IOException {
        String response = new Gson().toJson(new ImplInfo());
        httpRequest.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream body = httpRequest.getResponseBody();
        body.write(response.getBytes(StandardCharsets.UTF_8));
        body.close();
    }

}
