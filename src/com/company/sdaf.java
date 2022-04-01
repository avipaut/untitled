package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class sdaf {
    static Connection connection;

    public static void main(String[] args) {


        try {
            int id = 1; //вот сюда ты должен вернутб номер вопроса
            Scanner scan = new Scanner(System.in);
            System.out.println("Выберите тему: theme1,theme2,theme3");
            String theme = scan.nextLine(); //сделай красиво

            connection = DriverManager.getConnection("jdbc:sqlite:dasrtan");
            boolean isUserExist = false;
            try (PreparedStatement ps = connection.prepareStatement("SELECT question,variant1,variant2,variant3,answer FROM " + theme + " WHERE id = "+id+" ")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String question, variants1, variants2, variants3;
                        question = rs.getString("question");
                        variants1 = rs.getString("variant1");
                        variants2 = rs.getString("variant2");
                        variants3 = rs.getString("variant3");
                        int answer = rs.getInt("answer");

                        System.out.println(question);
                        System.out.println(variants1);
                        System.out.println(variants2);
                        System.out.println(variants3);
                        System.out.print("Праильный ответ?");
                        int answer1 = scan.nextInt();
                        if (answer == answer1) {
                            System.out.print("Угадал");
                        }
                    }
                    if (rs.next()) {
                        isUserExist = true;
                    }
                }
            }
            if (isUserExist) {
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

