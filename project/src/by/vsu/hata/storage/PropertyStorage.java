package by.vsu.hata.storage;

import by.vsu.hata.entity.Property;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyStorage {

    public static final String PRICE = "price";
    public static final String AREA = "area";
    public static final String ROOMS = "rooms";
    private static Map<String, String> emptyMap = new HashMap<>();
    static {
        emptyMap.put(PropertyStorage.PRICE, Double.toString(0d));
        emptyMap.put(PropertyStorage.AREA, Double.toString(0d));
        emptyMap.put(PropertyStorage.ROOMS, Double.toString(0d));
    }

    public static List<Property> getAll() {
        return Collections.emptyList();
    }

    public static Map<String, String> getEmptyMap() {
        return emptyMap;
    }
}
