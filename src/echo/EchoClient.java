package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Team 3
 */
public class EchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            System.out.println("Waiting For Connection...");
            
            //Used if the client and server on the same machine
            InetAddress localAddress = InetAddress.getLocalHost();

            try {
                //Create the Socket on the client side
                Socket clientSocket = new Socket(localAddress, 8181);
                
                //Create output to accept text to send to Server
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                //Create a reader to accept text from the socket
                InputStreamReader inputStreamRead = new InputStreamReader(clientSocket.getInputStream());
                
                //Creat buffer that stores incoming text from socket
                BufferedReader bufRead = new BufferedReader(inputStreamRead);

                System.out.println("Connected To Server");
                
                //Scanner accepts text from the console
                Scanner scanner = new Scanner(System.in);
                
                //Continue to ask for input until quit is typed
                while (true) {
                    System.out.print("Enter Text(Type \"quit\" to Exit): ");
                    String inputLine = scanner.nextLine();
                                        
                    //Send Text to socket and server
                    out.println(inputLine);
                    
                    //Accept text from server
                    String response = bufRead.readLine();
                    
                    //Check for "quit" command
                    if (inputLine.equalsIgnoreCase("quit")) {
                        System.out.println(response);
                        break;
                    }
                    else{
                        System.out.println("Server Response: " + response);
                    }
                }
            }
            catch(IOException ex2){
                System.out.println(ex2);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
