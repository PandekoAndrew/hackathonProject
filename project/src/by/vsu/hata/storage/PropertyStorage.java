package by.vsu.hata.storage;

import by.vsu.hata.Table;
import by.vsu.hata.entity.Property;

import java.util.*;

public class PropertyStorage {

    public static final String PRICE = "price";
    public static final String AREA = "area";
    public static final String LIST = "list";
    private static Map<String, String> emptyMap = new HashMap<>();
    static {
        emptyMap.put(PropertyStorage.PRICE, Double.toString(0d));
        emptyMap.put(PropertyStorage.AREA, Double.toString(0d));
        emptyMap.put(PropertyStorage.LIST, Table.getTable(Collections.emptyList()));
    }

    public static List<Property> getAll() {
        //return Collections.emptyList();
        List<Property> list = new ArrayList<>();
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 1228, 322, "дом1"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 2228, 322, "дом2"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 3228, 322, "дом3"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 4228, 322, "дом4"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 5228, 322, "дом5"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 6228, 322, "дом6"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 7228, 322, "дом7"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 8228, 322, "дом8"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 9228, 322, "дом9"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 10228, 322, "дом10"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 11228, 322, "дом11"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 12228, 322, "дом12"));

        return list;
    }

    public static Map<String, String> getEmptyMap() {
        return emptyMap;
    }
}
