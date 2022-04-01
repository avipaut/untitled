package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Dastan {
    static Connection connection;

    public static void main(String[] args) {

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:dasrtan");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter  name :");
                 String name = scanner.nextLine();

                System.out.println("Enter  password :");
                String password = scanner.nextLine();
                System.out.println("Enter role:\n1:Student \n2:Teacher");
                String role = scanner.nextLine();
                if (role.equals("1")){
                    role = "student";
                }else if (role.equals("2")){
                    role = "teacher";
                }
                String query = "INSERT INTO account (login, password,role) VALUES('" + name + "','" + password + "', '"+role+"');";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Rows added ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

    }
}

