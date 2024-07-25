// coffeeOS 1.0
/*
Copyright (c) 2024 Pear Computer LLC.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/*
coffeeOS, a fork of skyOS 3.0
*/

import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.lang.reflect.Method;

class Kernel {
  public static void print(String text) {
    System.out.println(text);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String command = null;
    String pwd = System.getProperty("user.dir");
    String echo = null;

    while (true) { // Use 'true' for an infinite loop
      print("Welcome to coffeeOS!");
      print("coffeeOS v1.0");
      print("coffeeOS> ");
      command = scanner.nextLine();

      switch (command) {
        case "help":
          print("help - Show this help list.");
          print("app - Load an app (com.example.test.TestApp is the name of the TestApp class for the test app).");
          print("echo - Echo some text.");
          break;
        case "app":
          print("Enter the app name (including class name): ");
          String appClassName = scanner.nextLine(); // Combined app name and class

          try {
            // Use ClassLoader.getSystemClassLoader() for simplicity
            Class<?> cls = Class.forName(appClassName);
            Object obj = cls.newInstance();
            Method method = cls.getDeclaredMethod("appMain");
            method.invoke(obj);
          } catch (ClassNotFoundException e) {
            print("Error: App class not found.");
          } catch (Exception e) { // Catch broader exceptions for better error handling
            e.printStackTrace(); // Print stack trace for debugging
          } finally {
            // Not necessary for these variables in this case, but good practice for resource management
            // appClassName = null;
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
