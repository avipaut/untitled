package com.company;

import java.sql.*;
import java.util.Scanner;

public class data_user {
    public static void main(String[] args) {
        data_user program= new data_user();
        program.open();
        select();
        program.close();

    }
        static Connection connection;
        void open () {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            } catch (Exception ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                System.out.println("Error");
            }
        }
        public static void select() {

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:project.db");
                Scanner check = new Scanner(System.in);
                System.out.println("Enter your name: ");
                String name = check.nextLine();
                boolean isUserExist = false;
                try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, SoftwareEngineering, Logic, German FROM items WHERE students = '" + name + "'")){
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()){
                            String English2,SoftwareEngineering,Logic,Math2,German;
                            Math2 = rs.getString("Math2");
                            English2 = rs.getString("English2");
                            SoftwareEngineering = rs.getString("SoftwareEngineering");
                            Logic = rs.getString("Logic");
                            German = rs.getString("German");
                            String name1 = rs.getString("students");
                            System.out.println(name1 + "\n" + "Math2: " + Math2 + "\n" + "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering + "\n" +  "Logic: " + Logic + "\n" + "German: " + German);
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
    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}


