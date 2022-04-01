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
//            enter_users();
            System.out.println("");
        }
        if (choose == 2) {
            register_users();
        }
    }//Выбор войти или регистр...

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
    }// добавляет логин и пароль в базу

    }




