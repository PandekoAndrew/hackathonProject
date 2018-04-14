package by.vsu.hata.handlers;

import by.vsu.hata.IntGetter;
import by.vsu.hata.entity.Property;
import by.vsu.hata.storage.PropertyStorage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        try {
            String response = new String(Files.readAllBytes(Paths.get("index.html")));
            response = replace(parseURI(t.getRequestURI().toString()), response);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String replace(Map<String, String> map, String page) {
        return page.replace("%price%", map.get(PropertyStorage.PRICE))
                .replace("%area%", map.get(PropertyStorage.AREA))
                .replace("%rooms%", map.get(PropertyStorage.ROOMS));
    }

    private Map<String, String> parseURI(String uri) throws Exception {
        List<Property> all = PropertyStorage.getAll();
        String[] split = uri.split("/");


        System.err.println("uri " + uri);
        System.err.println("split " + Arrays.toString(split));


        if (split.length == 1 || split.length == 0) {//средние данные по всему
            return getMap(PropertyStorage.getAll());
        }
        if (split.length == 3) {//по параметру
            switch (split[1]) {
                case "region":
                    return getMap(PropertyHandler.findByRegion(all, split[2]));
                case "city":
                    return getMap(PropertyHandler.findByCity(all, split[2]));
                case "rooms":
                    return getMap(PropertyHandler.findByRoomsAmount(all, Integer.parseInt(split[2])));
                case "type":
                    return getMap(PropertyHandler.findByType(all, split[2]));
                default:
                    //запрос дичь
                    return PropertyStorage.getEmptyMap();
            }
        } else {
            //404
            return PropertyStorage.getEmptyMap();
        }
    }

    private Map<String, String> getMap(List<Property> properties) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(PropertyStorage.PRICE, Double.toString(getAvgPrice(properties)));
        map.put(PropertyStorage.AREA, Double.toString(getAvgArea(properties)));
        map.put(PropertyStorage.ROOMS, Double.toString(getAvgRoomsAmount(properties)));
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
