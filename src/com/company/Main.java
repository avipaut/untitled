package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main program = new Main();
        program.open();
        enter_users();
        program.close();
}
    static Connection connection;

    void open() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.out.println("Error");
        }

    }
    public static void enter_users() {
        System.out.println("===== Enter =====");
        User user = new User();
        user.setName();
        user.setPassword();
        User.instance = user;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT name, password, role FROM " +
                    "users WHERE name = '" + user.getName() + "' and password = '"+ user.getPassword()+"'")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        int role1 = rs.getInt("role");
                        if (role1 == 1){
                            Student.student();
                        }
                        if (role1 ==2){
                            Teacher.teacher();
                        }
                    }

                    if (rs.next()){
                        isUserExist = true;

                    }
                }
            }
            if (isUserExist){
                System.out.println();



            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
