//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This contains the user class, attributes and functions such as login, register, logout and so on.


package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class User implements Serializable {
    private String username;
    private String password;
    
    User(){
        
    }
    
    public User(String un, String p){
        username = un;
        password = p;
    }
    
    public User(User u) {
        username = u.username;
        password = u.password;
    }

    public boolean Login(ArrayList<User> users, User u){  //retturns true if user exists
        boolean flag;
        
        flag = contains(users, u);
        
        return flag;
    }
    
    public User Register(ArrayList<User> users, User u){
        boolean flag = false, again = false, again1 = false;

        if(checkLimitUsername(u.GetUsername())){
            
            if (checkUsername(users, u.GetUsername())) {
                //Helper.DialogBox("Username already exists!");
                return null;
            }
        }
        else {
            Helper.DialogBox("Username should be less than 20 characters!");
            return null;
        }
        if(!checkLimitPassword(u.GetPassword())){
            return null;
        }
        return new User(u.GetUsername(), u.GetPassword());
    }
    
    public String[] changePassword(ArrayList<User> users){
        boolean flag = true, flag1 = true, flag2 = true;
        String arr[] = new String[3];
        Scanner cin = new Scanner(System.in);
        String user = null, pass = null, newPass = null;
        while(flag){
            System.out.print("Enter Username: ");
            
            user = cin.next();
            /*for (int i = 0; i < users.size(); i++) {
                    //System.out.println(users.get(i).GetUsername()+" "+users.get(i).GetPassword());
            }*/
            if (checkUsername(users, user)) {
                flag = false;
            }
            else {
                System.out.println("Username not found!");
            }
        }
        System.out.print("Enter old Password: ");
        User u;
        while(flag1) {
            pass = cin.next();
            u = new User(user, pass);
            //System.out.println("pass:" + password);
            if(contains(users, u)){
                flag1 = false;
            }
            else {
                System.out.println("Password wrong!\nRe-Enter old Password:");
            }
        }
        arr[0] = pass;  //saving old password
        System.out.print("\nEnter NEW Password: ");
        
        while (flag2) {   
            newPass = cin.next();
            if(checkLimitPassword(newPass)) {
                flag2 = false;
            }
            else {
                System.out.println("!\nRe-Enter NEW Password:");
            }
        }
        
        arr[1] = user;
        arr[2] = newPass;
        
        return arr;
    }
    
    public String[] changeUsername(ArrayList<User> users){
        boolean flag = true, flag1 = true, flag2 = true, flag3;
        String arr[] = new String[3];
        Scanner cin = new Scanner(System.in);
        String new_username = null, old_username = null, pass = null;
        
        while(flag){
            System.out.print("Enter old Username: ");
            old_username = cin.next();
            if (checkUsername(users, old_username)) {
                flag = false;
            }
            else {
                System.out.println("Username not found!");
            }
        }
        arr[0] = old_username;  //saving old username
        
        System.out.print("Enter Password: ");
        User u = null;
        while(flag1) {
            pass = cin.next();
            u = new User(old_username, pass);
            //System.out.println("pass:" + password);
            if(contains(users, u)){
                flag1 = false;
            }
            else {
                System.out.print("Password wrong!\nRe-Enter Password:");
            }
        }
        arr[2] = pass;
        
        System.out.print("\nEnter NEW Username: ");
        while (flag2){
            new_username = cin.next();
            flag3 = checkUsername(users, new_username);
            //System.out.println("flag: " + flag3);
            if (!flag3) {
                if(checkLimitUsername(new_username)){
                    flag2 = false;
                }
                else {
                    System.out.print("\nRe-enter NEW Username: ");
                }
            }
            else {
                if (flag3) {
                    System.out.println("Username Exists!");
                }
                System.out.print("Re-enter NEW Username: ");
            }
        }
        u.setUsername(new_username);
        arr[1] = new_username; //saving new username

        return arr;
    }
    
    public void print() {
        System.out.println("Username: " + username + "\tPassword: " + password);
    }
    
    public String GetPassword(){
        return password;
    }
    
    public String GetUsername(){
        return username;
    }
    
    public void setPassword(String p){
        password = p;
    }
    
    public void setUsername(String u){
        username = u;
    }
    
    public boolean contains(ArrayList<User> users, User u){ //returns true if it contains user
        boolean flag = false;
        for (int i = 0; i < users.size(); i++){
            //System.out.println(users.get(i).username + "\t" +u.username);
            if (users.get(i).GetUsername().equals(u.username)) {
                //System.out.println(users.get(i).password + "\t" + u.password);
                if (users.get(i).GetPassword().equals(u.password)) {
                    //System.out.println("in true");
                    flag = true;
                }
            }
        }
        return flag;
    }
    
    public boolean checkUsername(ArrayList<User> users, String u){ //returns true if username exists
        boolean flag = false;
        for (int i = 0; i < users.size(); i++){
            //System.out.println(users.get(i).username + "\t" + u);
            if (users.get(i).GetUsername().equals(u)) {
                //System.out.println(users.get(i).username);
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkLimitUsername(String username){
        boolean flag = true;
        if ((username.length() <= 0) || (username.length()> 20)) {
            flag = false;
        }

        return flag;
    }
    
    public boolean checkLimitPassword(String password){ //returns true if password within limit
        boolean flag = true;
        if ((password.length() < 4) || (password.length() > 10)) {
            //Helper.DialogBox("Password should be 4 to 10 characters long!");
            flag = false;
        }
        return flag;
    }
}
