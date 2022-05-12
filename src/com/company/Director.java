package com.company;

import java.sql.*;
import java.util.Scanner;

public class Director {
    static Connection connection;
    static void director() {


        System.out.println("""
                ===== Action =====
                1.Список предметов
                2.Список оценок
                3.Список заданий
                4.Список экзаменов
                5.Список зачетов
                6.Выставить оценки
                7.Выход
                """);
        Scanner student = new Scanner(System.in);
        int choose = student.nextInt();
        if (choose == 1) {
            allSubject();
            director();
                }
        if (choose == 2) {
            checkTeacher();
            director();
        }
        if (choose == 3) {
            registerTeachers();
            director();
        }
        if (choose == 4) {
            deleteTeachers();
            director();
        }
        if (choose == 5){
            registerStudents();
            director();
        }if (choose == 6){
            deleteStudent();
            director();
        }
        if (choose == 7) {
            Main.enter_users();
        }
    }

    public static void allSubject(){
        System.out.println("""
                1.Math2
                2.English
                3.SoftwareEngineering
                4.Logic
                5.German
                """);
    }
    public static void deleteTeachers() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            String query = "DELETE  FROM users WHERE name = '"+name+" 'AND role = 2";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteStudent() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            String query = "DELETE  FROM users WHERE name = '"+name+" 'AND role = 1";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void registerTeachers() {
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name :");
            String name = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();
            String query = "INSERT INTO users (name, password,role) VALUES('" + name + "','" + password + ",2');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void registerStudents() {
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name :");
            String name = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();
            String query = "INSERT INTO users (name, password,role) VALUES('" + name + "','" + password + ",1');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void checkTeacher() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT name FROM users WHERE role = 2")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String teacher = rs.getString("name");
                        System.out.println(teacher);
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
