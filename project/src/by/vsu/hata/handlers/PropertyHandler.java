package by.vsu.hata.handlers;

import by.vsu.hata.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyHandler {

    private List<Property> findByRegion(List<Property> properties, String region) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getRegion().equals(region)) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }

    private List<Property> findByCity(List<Property> properties, String city) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getCity().equals(city)) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }

    private List<Property> findByRoomsAmount(List<Property> properties, int amount) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getRoomsAmount() == amount) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }

    private List<Property> findByPropertyType(List<Property> properties, String type) {
        List<Property> resultSet = new ArrayList<>();
        for (Property property : properties) {
            if (property.getType().equals(type)) {
                resultSet.add(property);
            }
        }
        return resultSet;
    }
}
