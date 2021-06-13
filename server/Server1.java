//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This program is the server side of the program.


package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            //list of users and reading from file
            ArrayList<User> users = new ArrayList<>();
            users = Helper.ReadFromFile(users);

            //creating the socket
            ServerSocket ss = new ServerSocket(2222);

            System.out.println("server started...");

            while (true) {
                //waiting for a client
                Socket s = ss.accept();
                System.out.println("Client request here...");

                //making streams
                OutputStream out = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);

                InputStream in = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                
                //creating and starting a thread
                Thread thread = new TaskHandler(s, users, out, oos, in, ois);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
