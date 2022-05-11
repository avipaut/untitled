package com.company;

import java.util.Scanner;

public class User {

    public static User instance = null;

    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName() {
        this.name = fastEnter("Enter name:");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = fastEnter("Enter password:");
    }

    private String fastEnter(String enter){
        Scanner scanner = new Scanner(System.in);
        System.out.println(enter);
        String name = scanner.nextLine();
        return name;
    }
}
