// coffeeOS 1.0

/*
(Copyright and license information remains the same)
*/

/*
coffeeOS, a fork of skyOS 3.0
*/

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

class Kernel {
  public static void print(String text) {
    System.out.println(text);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String command = null;
    String pwd = System.getProperty("user.dir");
    String echo = null;
    // No need for ProcessBuilder here

    print("Welcome to coffeeOS!");
    print("coffeeOS v1.0");

    while (true) { // Use 'true' for an infinite loop
      print("coffeeOS> ");
      command = scanner.nextLine();

      switch (command) {
        case "help":
          print("help - Show this help list.");
          print("app - Load an app.");
          print("echo - Echo some text.");
          break;
        case "app":
          print("Enter the full app file path (including .java extension): ");
          String appPath = scanner.nextLine();

          try {
            // Use Runtime.getRuntime().exec to directly execute the .java file
            Runtime.getRuntime().exec("java " + appPath);
            print("App execution started.");
          } catch (IOException e) {
            print("Error: Could not execute app. " + e.getMessage());
          }
          break;
        case "echo":
          print("What to echo> ");
          echo = scanner.nextLine();
          print(echo);
          break;
        default:
          print("Invalid command.");
      }
    }
  }
}
