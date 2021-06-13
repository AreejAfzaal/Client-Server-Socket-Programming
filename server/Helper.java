//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This program contains useful helper functions used throughout the program such as writing and rvading file and displaying dialog box.


package server;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Helper {
    
    JFrame f;
    
    Helper() {
        
    }
    
    public static ArrayList<User> ReadFromFile(ArrayList<User> users) {
        try {
            //System.out.println("bro");
            File file = new File("C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\Client1\\users.txt");
            if (file.length() != 0) {
                
                Scanner fileReader = new Scanner(file);
                User u;
                
                String un, p;
                String str;
                String[] delimiter;

                while (fileReader.hasNext()){
                    str = fileReader.nextLine();
                    delimiter = str.split(",");
                    un = delimiter[0];
                    p = Helper.encrypt(delimiter[1]);
                    //System.out.println(p);
                    u = new User(un, p);
                    users.add(u);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static void ModifyFile(ArrayList<User> users){
        try(FileWriter fw = new FileWriter("users.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fout = new PrintWriter(bw))
        {
            for (int i = 0; i < users.size(); i++) {
                String line = users.get(i).GetUsername()+','+Helper.encrypt(users.get(i).GetPassword());
                fout.println(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        

    }
    
    public static void WriteToFile(String str){
        try(FileWriter fw = new FileWriter("users.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fout = new PrintWriter(bw))
        {
           
            fout.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void DialogBox(String str)
    {
        JOptionPane.showMessageDialog(null, str);
    }
    public static String encrypt(String pass){
        BitSet bitPass = null;
        final String charsetName = "US-ASCII"; 
        try {
            
            bitPass = BitSet.valueOf(pass.getBytes(charsetName));
            byte [] bytes = new byte[bitPass.length()/8];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i]=1;
            }
            BitSet identity = BitSet.valueOf(bytes);
            bitPass.xor(identity);                

        } catch (UnsupportedEncodingException ex) {
            
            ex.printStackTrace();
        }
        
        return new String(bitPass.toByteArray(), Charset.forName(charsetName));
    }
    
}

