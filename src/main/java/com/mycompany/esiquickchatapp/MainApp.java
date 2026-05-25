/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.esiquickchatapp;

import java.util.Scanner;

/**
 *
 * @author MakaB
 */
public class MainApp {
   

/**
 *
 * @author MakaB
 */
public class MainClass {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        // -- REGISTRATION SECTION --
        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African phone number (+27...): ");
        String phone = scanner.nextLine();

        String response = login.registerUser(username, password, phone);
        System.out.println(response);

        // If registration failed, stop here
        if (!response.equals("User registered successfully.")) {
            System.out.println("Registration failed. Please restart and try again.");
            scanner.close();
            return;
        }

        // -- LOGIN SECTION --
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine(); 

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        // Only enter the app if login succeeded
        if (!loggedIn) {
            System.out.println("Login failed. Please restart and try again.");
            scanner.close();
            return;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
}
    
