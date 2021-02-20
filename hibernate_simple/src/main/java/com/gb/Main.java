package com.gb;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Connection connection;
    static Statement stmt;

    /*
    CREATE TABLE students (
    id    INTEGER,
    name  TEXT,
    score INTEGER);
    */

    public static void main(String[] args) {
        try {
            connect();
            generateTable(Cat.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void generateTable(Class c) {
        if (!c.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Не возможно создать таблицу, не помечено аннотацией @Table");
        }

        Map<Class, String> converterMap = new HashMap<>();
        converterMap.put(int.class, "INTEGER");
        converterMap.put(String.class, "TEXT");


        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        //"CREATE TABLE "

        sb.append(((Table)c.getAnnotation(Table.class)).title());
        //"CREATE TABLE students"

        sb.append(" (");
        //"CREATE TABLE students ("

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Column.class)){
                sb.append(field.getName()).append(" ")
                        .append(converterMap.get(field.getType())).append(", ");
            }
        }

        //"CREATE TABLE students ("
        //    id INTEGER,
        //    name TEXT,
        //    score INTEGER,

        sb.setLength(sb.length()-2);
        sb.append(");");
        //"CREATE TABLE students ("
        //    id INTEGER,
        //    name TEXT,
        //    score INTEGER);

        String query = sb.toString();

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Ошибка подключения к БД");
        }
    }

    public static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
