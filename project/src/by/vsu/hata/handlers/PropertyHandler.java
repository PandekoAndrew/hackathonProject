package by.vsu.hata.handlers;

import by.vsu.hata.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyHandler {

    public static List<Property> findByRegion(List<Property> properties, String region) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getRegion().equals(region)) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }

    public static List<Property> findByCity(List<Property> properties, String city) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getCity().equals(city)) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }
}
