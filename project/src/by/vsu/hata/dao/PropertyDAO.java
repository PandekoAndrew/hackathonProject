package by.vsu.hata.dao;

import by.vsu.hata.entity.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO implements AbstractDAO<Property, Long> {
    private static final String SELECT_ALL = "SELECT * FROM property";
    private static final String SELECT_BY_ID = "SELECT * FROM property WHERE id = ?";
    private static final String INSERT = "INSERT INTO property(region, city, address, price, area, type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM property WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM property";
    
    @Override
    public List<Property> getAll() {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Property> properties = new ArrayList<Property>();

            while (resultSet.next()) {
                Property property = new Property();
                property.setId(resultSet.getLong(1));
                property.setRegion(resultSet.getString(2));
                property.setCity(resultSet.getString(3));
                property.setAddress(resultSet.getString(4));
                property.setPrice(resultSet.getInt(5));
                property.setArea(resultSet.getInt(6));
                property.setType(resultSet.getString(7));
                properties.add(property);
            }

            return properties;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Property getById(Long id) {

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Property property = new Property();
            if (resultSet.next()) {
                property.setId(id);
                property.setRegion(resultSet.getString(2));
                property.setCity(resultSet.getString(3));
                property.setAddress(resultSet.getString(4));
                property.setPrice(resultSet.getInt(5));
                property.setArea(resultSet.getInt(6));
                property.setType(resultSet.getString(7));
            }

            return property;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Property entity){
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, entity.getRegion());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getAddress());
            preparedStatement.setInt(4, entity.getPrice());
            preparedStatement.setInt(5, entity.getArea());
            preparedStatement.setString(6, entity.getType());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Property entity) {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
