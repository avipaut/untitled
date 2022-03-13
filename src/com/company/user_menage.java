package com.company;

import java.sql.*;
import java.util.Scanner;



public class user_menage {
    public static void main(String[] args) {
        user_menage program = new user_menage();
        program.open();
        program.close();
    }

    Connection connection;

    void open() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.out.println("Error");
        }
        Scanner num = new Scanner(System.in);
        byte choose;
        System.out.println("1: Enter       2:Registration");
        choose = num.nextByte();
        if (choose == 1) {
            enter_users();
        }
        if (choose == 2) {
            register_users();
        }
    }

    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void register_users() {
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            System.out.println("Enter your password :");
            String password = scanner.nextLine();
            String query = "INSERT INTO users (name, password) VALUES('" + name + "','" + password + "');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void enter_users() {
        try {
            Scanner check = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String name = check.nextLine();
            System.out.println("Enter your password");
            String password = check.nextLine();
            boolean isUserExist = false;
            assert connection != null;
            try (PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM users WHERE name = ? and password = ?")) {
                ps.setString(1, name);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isUserExist = true;
                    }
                }
            }
            if (isUserExist) {
                //studentsBody();
                System.out.println("Your rang is student");


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    }




