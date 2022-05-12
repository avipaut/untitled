package com.company;

import java.sql.*;
import java.util.Scanner;


public class Project {


    public static void main(String[] args) {
        enter_users();
        close();

    }

    static Connection connection;


    static void student() {


        System.out.println("""
                ===== Action =====
                1.Список предметов
                2.Список оценок
                3.Список заданий
                4.Список экзаменов
                5.Список зачетов
                6.Мой максимальный бал
                7.Мой минимальный бал
                8.Выход
                """);
        Scanner student = new Scanner(System.in);
        int choose = student.nextInt();
        if (choose == 1) {
            DataUser.select();
            student();
        }
        if (choose == 2) {
            Students.select();
        }
        if (choose == 3) {
            zdf.task();
        }
        if (choose == 8){
            System.out.println("sd");
        }


    }




    static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        public void register_users() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter  name :");
            String name = scanner.nextLine();
            System.out.println("Enter  password :");
            String password = scanner.nextLine();
            System.out.println("Enter role");
            String role = scanner.nextLine();
            String query = "INSERT INTO users (name, password,role) VALUES(" + name + "," + password + ", "+role+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                                student();
                                break;
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
    }
