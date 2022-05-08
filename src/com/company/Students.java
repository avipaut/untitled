package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Students {
    public static void main(String[] args) {
        Students program= new Students();
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
    }//Конектится в базе данных


    void close() {

        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//Закрывает базу данных


    public static void select() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            String name = Project.enter_users();
            boolean isUserExist = false;
            assert connection != null;
            try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, SoftwareEngineering, Logic, German FROM items WHERE students = '" + name + "'")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        double Math2,English2,SoftwareEngineering,Logic,German;
                        Math2 = getAvarage(rs.getString("Math2"));
                        English2 = getAvarage(rs.getString("English2"));
                        SoftwareEngineering = getAvarage(rs.getString("SoftwareEngineering"));
                        Logic = getAvarage(rs.getString("Logic"));
                        German = getAvarage(rs.getString("German"));
                        String name1 = rs.getString("students");
                        System.out.println(name1 + "\n" + "Math1: " + String.format("%.1f",Math2) + "\n" + "English: " +
                                String.format("%.1f",English2) + "\n" + "SoftwareEngineering: "
                                + String.format("%.1f",SoftwareEngineering) + "\n" +  "Logic: " + String.format("%.1f",Logic)
                                + "\n" + "German: " + String.format("%.1f",German));
                    }
                    if (rs.next()){
                        isUserExist = true;
                    }
                }
            }
            if (isUserExist){
                System.out.println("provlem");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//Выводит предметы и среднее арифметическое значение оценок


    static double getAvarage(String marksStr) {

        String[] marks = marksStr.split(",");
        double avarage = 0;
        for (String mark : marks) {
            avarage += Double.parseDouble(mark);
        }
        return avarage / marks.length;
    }//Вычисляет среднее арифметическое значение dfdffd
}
