// coffeeOS 1.0

/*
Copyright (c) 2024 Pear Computer LLC.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.  


THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL  
 THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES  
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  

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
        ProcessBuilder builder = null;
        Process process = null;
        BufferedReader reader = null;

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
                        builder = new ProcessBuilder("java", appPath);
                        process = builder.start();
                        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            print(line);  

                        }
                        process.waitFor();
                        int exitCode = process.exitValue();
                        print("App exited with code: " + exitCode);
                    } 
                    catch (IOException | InterruptedException e) {
                        print("Error: Could not execute app. " + e.getMessage());
                    } 
                    finally {
                        try {
                            if (reader != null) {
                                reader.close();
                            }
                            if (process != null) {
                                process.destroy();
                            }
                        } 
                        catch (IOException e) {
                            print("Error closing resources.");
                        }
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
