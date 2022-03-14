package com.company;


import java.sql.*;
import java.util.Scanner;




public class zdf {
    public static void main(String[] args) {
        zdf program = new zdf();
        program.open();
        program.delete_user();
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

    public void delete_user() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            String query = "DELETE  FROM items WHERE students = '"+name+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }// Удаляет пользователей


    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void task() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner check = new Scanner(System.in);
            System.out.println("Enter date: ");
            int date = check.nextInt();
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT date, Math2, English2, SoftwareEngineering, Logic, German FROM task WHERE date = " + date + "")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,Math2,German;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        int date1 = rs.getInt("date");
                        System.out.println("Задания на "+ date1 +" число"  + "\n" + "Math2: " + Math2 + "\n" + "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering + "\n" +  "Logic: " + Logic + "\n" + "German: " + German);
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
    }//выводи задания заданные на определённую дату
}
