package home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HomeModel {
    Connection conn = null;
    public ObservableList<DeliveriesData> deliveriesData;

    public HomeModel() {
        this.conn = dbConnection.getConnection();

        if (this.conn == null) {
            System.exit(0);
        }
    }

    public ObservableList<DeliveriesData> getDeliveries() {
        String query = "SELECT * FROM deliveries_tbl";

        try {
            this.deliveriesData = FXCollections.observableArrayList();

            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                this.deliveriesData.add(new DeliveriesData(
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)));
            }

            return deliveriesData;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void addDelivery(String model, String brand, String color, String city, String country, String date,
            String buyername) {
        String query = "INSERT INTO deliveries_tbl (model, brand, color, city, country, date, buyername) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);

            statement.setString(1, model);
            statement.setString(2, brand);
            statement.setString(3, color);
            statement.setString(4, city);
            statement.setString(5, country);
            statement.setString(6, date);
            statement.setString(7, buyername);

            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDelivery(String model, String brand, String color, String city, String country, String date,
            String buyername, String id) {
        String query = "UPDATE deliveries_tbl SET model=?, brand=?, color=?, city=?, country=?, date=?, buyername=? WHERE id=? ";
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);

            statement.setString(1, model);
            statement.setString(2, brand);
            statement.setString(3, color);
            statement.setString(4, city);
            statement.setString(5, country);
            statement.setString(6, date);
            statement.setString(7, buyername);
            statement.setInt(8, Integer.parseInt(id));

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteDelivery(String id) {
        String query = "DELETE FROM deliveries_tbl WHERE id=? ";
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
