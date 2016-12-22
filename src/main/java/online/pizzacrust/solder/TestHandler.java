package online.pizzacrust.solder;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        for (String string : toParameters(httpExchange.getRequestURI().toString())) {
            System.out.println(string);
        }
    }

    public static String[] toParameters(String query) {
        //http://test.com/modpack/meow
        String withoutRoot = query.replace(Main.CONFIGURATION.rootURL + "/", "");
        String parameters = withoutRoot.replace("/", " ");
        return parameters.split(" ");
    }
}
