package by.vsu.hata.storage;

import by.vsu.hata.Table;
import by.vsu.hata.dao.PropertyDAO;
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
//        PropertyDAO dao = new PropertyDAO();
//        return dao.getAll();
        List<Property> list = new ArrayList<>();
        list.add(new Property(0L, "витебская область", "витебск", "http://vsu.by/", 1228, 1, "дом1"));
        list.add(new Property(0L, "витебская область", "витебск", "http://vsu.by/", 2228, 322, "дом2"));
        list.add(new Property(0L, "витебская область", "verh", "http://vsu.by/", 3228, 322, "дом3"));
        list.add(new Property(0L, "витебская область", "витебск", "http://vsu.by/", 4228, 322, "дом4"));
        list.add(new Property(0L, "витебская область", "витебск", "http://vsu.by/", 5228, 322, "дом5"));
        list.add(new Property(0L, "витебская область", "витебск", "http://vsu.by/", 6228, 322, "дом6"));
        list.add(new Property(0L, "витебская область", "minsk", "строителей", 7228, 322, "дом7"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 8228, 322, "дом8"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 9228, 322, "дом9"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 10228, 322, "дом10"));
        list.add(new Property(0L, "витебская область", "minsk", "строителей", 11228, 322, "дом11"));
        list.add(new Property(0L, "витебская область", "витебск", "строителей", 12228, 322, "дом12"));

        return list;
    }

    public static Map<String, String> getEmptyMap() {
        return emptyMap;
    }
}
