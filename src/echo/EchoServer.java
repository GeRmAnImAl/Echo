package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Team 3
 */
public class EchoServer {

    public static void main(String[] args) {
        
        
        try {
            
            // Create the socket to connect to and allow the client to connnect
            ServerSocket serverSocket = new ServerSocket(8181);
            
            System.out.println("Waiting For Connection...");
            
            //Accept the incoming connection to the server
            Socket clientSocket = serverSocket.accept();
            
            System.out.println("Connected To Client");

            //Get the string from the socket, create an input stream, the add to the buffer
            try{
                
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader bufRead = new BufferedReader(inputStreamReader); 
                
                // Printwriter will send the string back out to the client through the socket
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                
                //While the buffer is not empty, print out the string and send it back out to the client
                while ((inputLine = bufRead.readLine()) != null) {
                    
                    //If the quit command is recieved, close the connection with the client
                    if(inputLine.equalsIgnoreCase("quit")){
                        System.out.println("Client Session Terminated.");
                        out.println("Disconnected.");
                        clientSocket.close();
                        break;
                    }
                    System.out.println("Client Request: " + inputLine);
                    out.println(inputLine);
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
