//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This program will be called on the server side and contains the code that will deal with the databbase/file.

package server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TaskHandler extends Thread{
    Socket socket;
    OutputStream out;
    ObjectOutputStream oos;
    ArrayList<User> users;
    InputStream in;
    ObjectInputStream ois;
    
    //constructor of the class
    public TaskHandler(Socket socket, ArrayList<User> users, OutputStream out, ObjectOutputStream oos, InputStream in, ObjectInputStream ois) {
        this.users = users;
        this.out = out;
        this.oos = oos;
        this.in = in;
        this.ois = ois;
    }

    @Override
    public void run(){

        User u;
        int option;
        try {
            while (true) {
                
                option = in.read();
                System.out.println("option: " + option);
                if (option == 1) {
                    
                    ///LOGIN
                    System.out.println("in login");
                    u = (User) ois.readObject();
                    System.out.println("Login\nuser: " + u.GetUsername() + "  pass: " + u.GetPassword());
                    if (!u.Login(users, u)) {
                        Helper.DialogBox("Login Unsuccessful");
                        System.out.println("Login Unsuccessful");
                        oos.writeObject(false);

                    } else {
                        Helper.DialogBox("Login Successful");
                        System.out.println("\nWelcome User \'" + u.GetUsername() + "\' !");
                        oos.writeObject(true);
                        boolean loggedIn = true;
                        //Code for change password
                        while (loggedIn) {
                            int value = in.read();
                            System.out.println(value);
                            if (value == 1) {
                                
                                //Change Username
                                User changedUser = (User) ois.readObject();
                                for (int i = 0; i < users.size(); i++) {
                                    if (users.get(i).GetUsername().equals(changedUser.GetUsername())){
                                        if (users.get(i).GetUsername().equals(u.GetUsername())) {
                                            users.set(i, changedUser);
                                            u.setUsername(changedUser.GetUsername());
                                            //u.GetUsername()
                                            System.out.println(u.GetUsername());
                                        }
                                    }
                                }

                                Helper.ModifyFile(users);
                                oos.writeObject(changedUser);


                            } else if (value == 2) {
                                
                                //Change Password
                                User changedUser = (User) ois.readObject();
                                for (int i = 0; i < users.size(); i++) {
                                    if (users.get(i).GetUsername().equals(u.GetUsername())) {
                                        users.set(i, changedUser);
                                        u.setPassword(changedUser.GetPassword());
                                        System.out.println(u.GetUsername());
                                    }
                                }
                                Helper.ModifyFile(users);
                                oos.writeObject(changedUser);

                            } else if (value == 3) {
                                //Logout
                                loggedIn = false;
                            }

                        }
                    }
                    System.out.println("done");
                } else if (option == 2) {
                    //ReGISTVR
                    u = (User) ois.readObject();
                    System.out.println("Register:\nuser: " + u.GetUsername() + "  pass: " + u.GetPassword());


                    u = u.Register(users, u);
                    if (u != null) {
                        users.add(u);
                        String encryptedPass = Helper.encrypt(u.GetPassword());
                        Helper.WriteToFile(u.GetUsername() + "," + encryptedPass);

                        System.out.println("User Created Successfully!");
                        oos.writeObject(true);
                    } else {
                        oos.writeObject(false);
                    }
                }
                else{
                    socket.close();
                    System.out.println("here");
                }

                //s.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client Connection Ended!!!");
            //e.printStackTrace();
        }
    }
}
