package com.example.sqlsecurity;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_API {
    private static Connection connection = ConnectionProvider.getConnection();

    private DB_API(){
    }

    public static List<Person> readPersons(int page, int size) throws SQLException {
        if(page<=0||size<=0)throw new IllegalArgumentException();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from persons order by person_id offset ? rows fetch next ? rows only");
        preparedStatement.setInt(1,(page-1)*size);
        preparedStatement.setInt(2,size);
        ResultSet rs = preparedStatement.executeQuery();
        List<Person> ls = new ArrayList<>();
        while(rs.next()){
            ls.add(new Person(
                    rs.getBigDecimal("person_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getTimestamp("birthday")
            ));
        }
        return ls;
    }

    public static boolean insertPerson(BigDecimal person_id, String name, String gender, String birthday) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select 1 from persons where person_id=?");
        preparedStatement.setBigDecimal(1,person_id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) return false;
        preparedStatement = connection.prepareStatement("insert into persons values (?,?,?,to_date(?,'yyyy-MM-dd'))");
        preparedStatement.setBigDecimal(1,person_id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,gender);
        preparedStatement.setString(4,birthday);
        preparedStatement.executeUpdate();
        return true;
    }

    public static void updatePerson(BigDecimal person_id, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update persons set name=? where person_id=?");
        preparedStatement.setString(1,name);
        preparedStatement.setBigDecimal(2,person_id);
        preparedStatement.executeUpdate();
    }

    public static void deletePerson(BigDecimal person_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from persons where person_id=?");
        preparedStatement.setBigDecimal(1,person_id);
        preparedStatement.executeUpdate();
    }

    public static List<BigDecimal> getPersonIds() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select person_id from persons order by person_id");
        List<BigDecimal> ls = new ArrayList<>();
        while(rs.next()) ls.add(rs.getBigDecimal("person_id"));
        return ls;
    }

    public static List<Transport> readTransports(int page, int size) throws SQLException {
        if(page<=0||size<=0)throw new IllegalArgumentException();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from transports order by transport_id offset ? rows fetch next ? rows only");
        preparedStatement.setInt(1,(page-1)*size);
        preparedStatement.setInt(2,size);
        ResultSet rs = preparedStatement.executeQuery();
        List<Transport> ls = new ArrayList<>();
        while(rs.next()){
            ls.add(new Transport(
                    rs.getBigDecimal("transport_id"),
                    rs.getBigDecimal("owner_id"),
                    rs.getString("license_plate"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getBigDecimal("model_year"),
                    rs.getString("color")
            ));
        }
        return ls;
    }

    public static boolean insertTransport(BigDecimal transport_id, BigDecimal owner_id, String license_plate, String brand, String model, BigDecimal model_year, String color) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select 1 from transports where transport_id=?");
        preparedStatement.setBigDecimal(1,transport_id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) return false;
        preparedStatement = connection.prepareStatement("insert into transports values (?,?,?,?,?,?,?)");
        preparedStatement.setBigDecimal(1,transport_id);
        preparedStatement.setBigDecimal(2,owner_id);
        preparedStatement.setString(3,license_plate);
        preparedStatement.setString(4,brand);
        preparedStatement.setString(5,model);
        preparedStatement.setBigDecimal(6,model_year);
        preparedStatement.setString(7,color);
        preparedStatement.executeUpdate();
        return true;
    }

    public static void updateTransport(BigDecimal transport_id, BigDecimal owner_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update transports set owner_id=? where transport_id=?");
        preparedStatement.setBigDecimal(1,owner_id);
        preparedStatement.setBigDecimal(2,transport_id);
        preparedStatement.executeUpdate();
    }

    public static void deleteTransport(BigDecimal transport_id) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from driving_rights where transport_id=?");
        preparedStatement.setBigDecimal(1,transport_id);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("delete from right_log where transport_id=?");
        preparedStatement.setBigDecimal(1,transport_id);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("delete from transports where transport_id=?");
        preparedStatement.setBigDecimal(1,transport_id);
        preparedStatement.executeUpdate();
    }

    public static List<BigDecimal> getTransportIds() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select transport_id from transports order by transport_id");
        List<BigDecimal> ls = new ArrayList<>();
        while(rs.next()) ls.add(rs.getBigDecimal("transport_id"));
        return ls;
    }

    public static List<String> getBrands() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from brands");
        List<String> ls = new ArrayList<>();
        while(rs.next()) ls.add(rs.getString("brand"));
        return ls;
    }

    public static List<String> getColors() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from colors");
        List<String> ls = new ArrayList<>();
        while(rs.next()) ls.add(rs.getString("color"));
        return ls;
    }

    public static List<String> getPlates() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from license_plates");
        List<String> ls = new ArrayList<>();
        while(rs.next()) ls.add(rs.getString("license_plate"));
        return ls;
    }

    public static String getHash(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select password from AUTH where username=?");
        preparedStatement.setString(1,username);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) return rs.getString("password");
        else return null;
    }

    public static boolean setUser(String username, String hash) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select 1 from AUTH where username=?");
        preparedStatement.setString(1,username);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) return false;
        preparedStatement = connection.prepareStatement("insert into Auth values (?,?)");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,hash);
        preparedStatement.executeUpdate();
        return true;
    }

    public static void updateUser(String username, String hash) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update auth set password=? where username=?");
        preparedStatement.setString(1,hash);
        preparedStatement.setString(2,username);
        preparedStatement.executeUpdate();
    }
}
