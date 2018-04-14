package by.vsu.hata.handlers;

import by.vsu.hata.IntGetter;
import by.vsu.hata.entity.Property;
import by.vsu.hata.storage.PropertyStorage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        try {
            parseURI(t.getRequestURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseURI(String uri) throws Exception {
        List<Property> all = PropertyStorage.getAll();
        String[] split = uri.split("/");
        if (split.length == 2) {
            switch (split[0]) {
                case "region":
                    return getMap(PropertyHandler.findByRegion(all, split[1]));
                case "city":
                    return getMap(PropertyHandler.findByCity(all, split[1]));
                case "rooms":
                    return getMap(PropertyHandler.findByRoomsAmount(all, Integer.parseInt(split[1])));
                case "type":
                    return getMap(PropertyHandler.findByType(all, split[1]));
                default:
                    //запрос дичь
                    return Collections.emptyMap();
            }
        } else {
            //404
            return Collections.emptyMap();
        }
    }

    private Map<String, String> getMap(List<Property> properties) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("price", Double.toString(getAvgPrice(properties)));
        map.put("area", Double.toString(getAvgArea(properties)));
        map.put("rooms", Double.toString(getAvgRoomsAmount(properties)));
        return map;
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
