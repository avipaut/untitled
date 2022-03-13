package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main program = new Main();
        program.open();
        program.marks();
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

    }
    void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void marks() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:project.db");

            Scanner scanner = new Scanner(System.in);
            String name,marks,subject;
            System.out.print("Enter students name: ");
            name = scanner.nextLine();
            System.out.println("""
                    Enter subject:
                    1.Math2
                    2.English2
                    3.SoftwareEngineering
                    4.Logic
                    5.German""");
            subject = scanner.nextLine();
            System.out.print("Enter marks: ");
            marks = scanner.nextLine();
            String query = "UPDATE items SET "+ subject +" = "+ subject +" || '," + marks + "' WHERE students = '" + name + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
