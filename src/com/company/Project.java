package com.company;

import java.sql.*;
import java.util.Scanner;


public class Project {


    public static void main(String[] args) {
        Project program = new Project();
        program.open();
        program.enter_users();
        program.register_users();
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
        System.out.println("===== Enter =====");
        enter_users();
    }

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
            data_user.select();
        }
        if (choose == 2) {
            Students.select();
        }
        if (choose == 3) {
            zdf.task();
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

    public void enter_users() {

        String name,password;
        {

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:dasrtan");
                Scanner check = new Scanner(System.in);
                System.out.println("Enter your name: ");
                name = check.nextLine();
                System.out.println("Enter password");
                password = check.nextLine();
                boolean isUserExist = false;
                try (PreparedStatement ps = connection.prepareStatement("SELECT login, password, role FROM account WHERE name = '" + name + "' and password = '"+ password+"'")){
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()){
                            String  role1;
                            role1 = rs.getString("role");
                            if (role1.equals("student")){
                                student();//тут твоя ф-я
                            }else if (role1.equals("teacher")){
                                System.out.println(("hallo teacher"));//аналогично
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
                System.out.println("sdasd");
            }

        }

    }
}