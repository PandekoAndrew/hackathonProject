package by.vsu.hata.handlers;

import by.vsu.hata.IntGetter;
import by.vsu.hata.entity.Property;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        parseURI(t.getRequestURI().toString());

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void parseURI(String uri) {
        String[] split = uri.split("/");
        if (split.length == 2) {
            switch (split[0]) {
                case "region":
                    break;
                case "city":
                    break;
                case "rooms":
                    break;
                case "type":
                    break;
            }
        } else {
            //404
        }
    }

    private double getAvgPrice(List<Property> properties) throws Exception {
        return getAvg(properties, p -> p.getPrice());
    }

    private double getAvgArea(List<Property> properties) throws Exception {
        return getAvg(properties, p -> p.getArea());
    }

    private double getAvgRoomsAmount(List<Property> properties) throws Exception {
        return getAvg(properties, p -> p.getRoomsAmount());
    }

    private double getAvg(List<Property> properties, IntGetter intGetter) throws Exception {
        double avgRoomsAmount = 0d;
        if (!properties.isEmpty()) {
            for (Property property : properties) {
                avgRoomsAmount += intGetter.get(property);
            }
            return avgRoomsAmount / properties.size();
        } else {
            return avgRoomsAmount;
        }
    }
}
