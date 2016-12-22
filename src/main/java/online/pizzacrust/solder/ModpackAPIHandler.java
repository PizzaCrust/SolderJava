package online.pizzacrust.solder;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ModpackAPIHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpRequest) throws IOException {
        String[] parameters = TestHandler.toParameters(httpRequest.getRequestURI().toString());
        if (parameters.length == 1) {
            String response = new Gson().toJson(Main.MODPACK_DATABASE);
            httpRequest.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream body = httpRequest.getResponseBody();
            body.write(response.getBytes(StandardCharsets.UTF_8));
            body.close();
        } else if (parameters.length > 1) {
            String slug = parameters[1];
            for (String registeredSlug : Main.MODPACK_DATABASE.modpacksOnDatabase.keySet()) {
                if (registeredSlug.equals(slug)) {
                    ModpackInfo info = ModpackInfo.fromfile(registeredSlug);
                    String response = new Gson().toJson(info);
                    httpRequest.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                    OutputStream body = httpRequest.getResponseBody();
                    body.write(response.getBytes(StandardCharsets.UTF_8));
                    body.close();
                }
            }
            String response = new Gson().toJson(new ModpackNotExistantInfo());
            httpRequest.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream body = httpRequest.getResponseBody();
            body.write(response.getBytes(StandardCharsets.UTF_8));
            body.close();
        }
    }

    public static class ModpackNotExistantInfo {
        public String status = "404";
        public String error = "Modpack does not exist";
    }

}
