package by.vsu.hata;

import by.vsu.hata.entity.Property;

import java.util.List;

public class Table {

    public static String getTable(List<Property> properties) {
        StringBuilder table = new StringBuilder("<table class=\"table_blur\"><tr>" +
                "<th>Область</th>" +
                "<th>Город</th>" +
                "<th>Стоимость</th>" +
                "<th>Площадь</th>" +
                "<th>Тип</th>" +
                "<th></th></tr>");
//        int counter = 0;
        for (Property property : properties) {
            table.append("<tr>");
            table.append(getTableRow(property));
            table.append("</tr>");
//            counter++;
//            if (counter == 10) {
//                break;
//            }
        }
        table.append("</table></center>");
        return properties.isEmpty() ? "" : table.toString();
    }

    private static String getTableRow(Property property) {
        return "<td>" + property.getRegion() + "</td>" +
                "<td>" + property.getCity() + "</td>" +
                "<td>" + property.getPrice() + "</td>" +
                "<td>" + property.getArea() + "</td>" +
                "<td>" + property.getType() + "</td>" +
                "<td><a href=\"" + property.getAddress() + "\">Ссылка</a></td>";
    }
}
