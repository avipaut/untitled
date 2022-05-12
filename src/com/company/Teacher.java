package com.company;

import java.sql.*;
import java.util.Scanner;

public class Teacher {
    static void teacher() {


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
            select();
            teacher();
        }
        if (choose == 2) {
            selectAvarage();
            teacher();
        }
        if (choose == 3) {
            task();
            teacher();
        }
        if (choose == 4) {
            teacher();
        }
        if (choose == 5) {
            scoreCheck();
            teacher();
        }
        if (choose == 6){
            marks();
            teacher();
        }
        if (choose == 7) {
            Main.enter_users();
        }
    }
    public static void select() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, SoftwareEngineering, Logic, German FROM items ")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,Math2,German;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        String name1 = rs.getString("students");
                        System.out.println(name1 + "\n" + "Math2: " + Math2 + "\n" + "English: " + English2 + "\n" +
                                "SoftwareEngineering: " + SoftwareEngineering + "\n" +  "Logic: " + Logic + "\n" +
                                "German: " + German +"\n");

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

            System.out.println("Extension"+e);
        }
    }

    public static void selectAvarage() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;

            try (PreparedStatement ps = connection.prepareStatement("SELECT students, Math2, English2, " +
                    "SoftwareEngineering, Logic, German FROM items")){
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
                                + "\n" + "German: " + String.format("%.1f",German)+"\n");
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
    }

    public static void task() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner check = new Scanner(System.in);
            System.out.println("Enter date: ");
            int date = check.nextInt();
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT date, Math2, English2," +
                    " SoftwareEngineering, Logic, German FROM task WHERE date = " + date + "")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,Math2,German;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        int date1 = rs.getInt("date");
                        System.out.println("Задания на "+ date1 +" число"  + "\n" + "Math2: " + Math2 + "\n" +
                                "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering + "\n" +
                                "Logic: " + Logic + "\n" + "German: " + German);
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

    public static void scoreCheck() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT date, Math2, English2," +
                    " SoftwareEngineering, Logic, German FROM score ")){
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String English2,SoftwareEngineering,Logic,German,Math2;
                        Math2 = rs.getString("Math2");
                        English2 = rs.getString("English2");
                        SoftwareEngineering = rs.getString("SoftwareEngineering");
                        Logic = rs.getString("Logic");
                        German = rs.getString("German");
                        int date1 = rs.getInt("date");
                        System.out.println("Рассписание зачётов на "+ date1 +" число"  + "\n" + "Math2: " + Math2 +
                                "\n" + "English: " + English2 + "\n" + "SoftwareEngineering: " + SoftwareEngineering +
                                "\n" +  "Logic: " + Logic + "\n" + "German: " + German +  "\n" );
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

    public static void marks() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");
            Scanner scanner = new Scanner(System.in);
            String name,marks,subject;
            System.out.print("Enter students name: ");
            name = scanner.nextLine();
            System.out.println("""
                    Enter subject
                    1.Math2
                    2.English2
                    3.SoftwareEngineering
                    4.Logic
                    5.German""");
            subject = scanner.nextLine();
            System.out.print("Enter marks: ");
            marks = scanner.nextLine();
            String query = "UPDATE items SET "+ subject +" = "+ subject +" || '," + marks + "' WHERE students = '" +
                    name + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    static Connection connection;
    public void add_exam() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter subject :");
            String subject = scanner.nextLine();
            System.out.println("Enter date :");
            String date = scanner.nextLine();
            System.out.println("Enter time");
            String time = scanner.nextLine();
            String query = "INSERT INTO exam ("+subject+", date) VALUES('" + time + "'," + date + ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Rows added ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static double getAvarage(String marksStr) {

        String[] marks = marksStr.split(",");
        double avarage = 0;
        for (String mark : marks) {
            avarage += Double.parseDouble(mark);
        }
        return avarage / marks.length;
    }

}
