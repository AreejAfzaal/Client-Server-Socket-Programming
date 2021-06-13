//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This program is the client side of the code.

package client1;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {

    public static void main(String[] args) throws IOException{


        //creating a new socket
        Socket s = new Socket("localhost", 2222);
        try {
            //making the input and ooutput streams
            OutputStream out = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            
            InputStream in = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);

            //calling the GUI to be displayed
            GUI gui = new GUI(s, out, oos, in, ois);

            //s.close();
            
        } catch(Exception e){
            System.out.println("end");
            s.close();
            e.printStackTrace();
        }
        
    }
    
}
