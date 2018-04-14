package by.vsu.hata.handlers;

import by.vsu.hata.IntGetter;
import by.vsu.hata.Table;
import by.vsu.hata.entity.Property;
import by.vsu.hata.storage.PropertyStorage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        try {
            String response = new String(Files.readAllBytes(Paths.get("index.html")));
            response = replace(parseURI(t.getRequestURI().toString()), response);
            byte[] bs = response.getBytes("UTF-8");
            t.sendResponseHeaders(200, bs.length);
            OutputStream os = t.getResponseBody();
            os.write(bs);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String replace(Map<String, String> map, String page) {
        return page.replace("%price%", map.get(PropertyStorage.PRICE))
                .replace("%area%", map.get(PropertyStorage.AREA))
                .replace("%list%", map.get(PropertyStorage.LIST));
    }

    private Map<String, String> parseURI(String uri) throws Exception {
        List<Property> all = PropertyStorage.getAll();
        String[] split = uri.split("/");
        if (split.length == 1 || split.length == 0) {//средние данные по всему
            return getMap(PropertyStorage.getAll());
        }
        if (split.length == 3) {//по параметру
            switch (URLDecoder.decode(split[1])) {
                case "region":
                    return getMap(PropertyHandler.findByRegion(all, URLDecoder.decode(split[2])));
                case "city":
                    return getMap(PropertyHandler.findByCity(all, URLDecoder.decode(split[2])));
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
        properties.sort(Comparator.comparingDouble(o -> o.getPrice() / ((double) o.getArea())));
        map.put(PropertyStorage.LIST, Table.getTable(properties));
        return map;
    }

    private double getAvgPrice(List<Property> properties) throws Exception {
        return getAvg(properties, p -> p.getPrice());
    }

    private double getAvgArea(List<Property> properties) throws Exception {
        return getAvg(properties, p -> p.getArea());
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
