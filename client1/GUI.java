//Areej Afzaal
//l174189@lhr.nu.edu.pk
//AP-6A
//This iis the GUI of the program which will be shown to the client and will take input from the client.

package client1;
import server.Helper;
import server.User;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

public class GUI extends JFrame implements ActionListener{
    
    boolean addFlag = false, updateFlag = false, deleteFlag = false, searchNameFlag = false, searchCnicFlag = false;
    JButton registerButton, loginButton, changePass, changeUser, logoutButton, enterButton, enterButton1, enterButton2;
    JLabel label1, userLabel, passLabel, welcomeLabel, oldUserLabel, newUserLabel, passUserLabel, userPassLabel, oldPassLabel, newPassLabel, userRLabel, passRLabel;
    JPanel p, p1;
    JTextField   passUserText, passText, showRS, userText, oldUserText, newUserText, userPassText, oldPassText, newPassText, userRText, passRText;

    Border loginBorder, registerBorder;
    static JFrame f = new JFrame("Login"), dialogFrame = new JFrame("Message"), f1 = new JFrame(), f2 = new JFrame(), f3 = new JFrame(), f4 = new JFrame();
    Font font1 = new Font(Font.MONOSPACED, Font.BOLD , 16), font2 = new Font(Font.SERIF, Font.ITALIC , 14);
    Border wlcmBorder = new MatteBorder(new Insets(30, 25, 180, 25), new ImageIcon("C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\guitmp\\src\\guitmp\\bgblu.jpg")), signUpBorder = new MatteBorder(new Insets(30, 25, 130, 25), new ImageIcon("C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\guitmp\\src\\guitmp\\green.png"));;
    
    boolean loginFlag, registerFlag;
    String username, password, registerUser, registerPassword;
    ObjectOutputStream oos;
    OutputStream output;
    InputStream in;
    ObjectInputStream ois;
    Socket s;

    User loggedInUser;
    
    //constructor of the GUI
    public GUI(Socket s, OutputStream output, ObjectOutputStream oos, InputStream in, ObjectInputStream ois) throws IOException {
        this.s = s;
        this.output = output;
        this.oos = oos;
        this.in = in;
        this.ois = ois;

        initMethod();

    }
    
    
    //designs the main screen of the address book
    public void initMethod() throws IOException {

        //making the label components
        label1=new JLabel("*****SOCIAL NETWORKING SITE*****");
        userLabel = new JLabel("Username: ");
        passLabel = new JLabel("Password: ");
        userText = new JTextField(25);
        passText = new JTextField(25);

        //making the button components
        loginButton=new JButton("Login");
        registerButton=new JButton("Register");

        //attaching the action listeners of the buttons
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);


        //Get the window toolkit
        Toolkit theKit = f.getToolkit();

        //Get the screen size and set window size to half and the position to approximately center of the screen
        Dimension wndSize = theKit.getScreenSize();
        System.out.println(wndSize.height + " " + wndSize.width);
        f.setBounds(10, 10, 370, 600);  // Position and Size
        
        //setting the bounds
        label1.setBounds(10, 10, 350, 70);
        userLabel.setBounds(50, 150, 100, 30);
        passLabel.setBounds(50, 220, 100, 30);
        userText.setBounds(150, 150, 150, 30);
        passText.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registerButton.setBounds(200, 300, 100, 30);

        //setting the colour of the text labels
        userLabel.setForeground(new Color(139,0,0));
        passLabel.setForeground(new Color(139,0,0));

        //setting the font style and font size of the text label
        label1.setFont(font1);
        userLabel.setFont(font1);
        passLabel.setFont(font1);

        //setting a custom border around the title of the frame
        Border titleBorder = new MatteBorder(new Insets(20, 10, 20, 10), new ImageIcon("C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\HW2\\src\\hw2\\bg.png"));
        label1.setBorder(titleBorder);

        //setting the border of the buttons
        loginBorder = new LineBorder(new Color(34,139,34), 2);
        //registerBorder = new LineBorder(new Color(255,165,0), 2);
        
        loginButton.setBackground(new Color(154,205,50));
        registerButton.setBackground(new Color(154,205,50));
        loginButton.setBorder(loginBorder);
        registerButton.setBorder(loginBorder);

        //adding all the components to the container
        Container con=f.getContentPane();
        con.setLayout(null);
        con.add(label1);
        con.add(userLabel);
        con.add(userText);
        con.add(passLabel);
        con.add(passText);
        con.add(loginButton);
        con.add(registerButton);
        
        //setting some other properties of the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        
        
    }


    //a function to set JFormattedText field (cnic and phone number) to take input in a specific way
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            
            formatter = new MaskFormatter(s);
            
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    
    public void showFullPanel(){

        //cretaing a container and setting its layout to flow layout
        Container con = f1.getContentPane();
        con.removeAll();
        con.setLayout(new FlowLayout());

        //creating text fields and formatted text fields
        welcomeLabel = new JLabel("Welcome to your HomePage!");
        welcomeLabel.setBorder(wlcmBorder);
        
        //creating the enter button and setting its properties
        logoutButton = new JButton("Logout");
        changePass = new JButton("Change Password");
        changeUser = new JButton("Change Username");
        
        //logout button and its properties
        logoutButton.setPreferredSize(new Dimension(100,30));
        logoutButton.setBorder(new LineBorder(Color.PINK, 2));
        
        //password box and its properties
        changePass.setPreferredSize(new Dimension(230,25));
        changePass.setBackground(Color.LIGHT_GRAY);
        changePass.setOpaque(false);
        changePass.setFont(font2);
        changePass.setBorder(null);
        changePass.setForeground(new Color(0,0,255));
        
        //user box and its properties
        changeUser.setPreferredSize(new Dimension(230,25));
        changeUser.setBorder(null);
        changeUser.setOpaque(false);
        changeUser.setBackground(Color.LIGHT_GRAY);
        changeUser.setFont(font2);
        changeUser.setForeground(new Color(0,0,255));

        //adding the component to container
        con.add(welcomeLabel);
        con.add(changeUser);
        con.add(changePass);
        con.add(logoutButton);

        //getting the window toolkit to set the size and position of the container accordingly
        f1.setBounds(10, 10, 300, 450);  // Position and Size

        
        welcomeLabel.setFont(font1);
        welcomeLabel.setForeground(new Color(0,0,139));

        //setting some other basic properties on the container
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setResizable(false);
        con.setBackground(new Color(230,230,250));
        
        logoutButton.addActionListener(this);
        changePass.addActionListener(this);
        changeUser.addActionListener(this);
    }
    
    public void changeUsernamePanel() {
        
        Container con = f2.getContentPane();
        con.removeAll();
        con.setLayout(new FlowLayout());

        //creating text fields and formatted text fields
        welcomeLabel = new JLabel("*****Change Username*****");
        welcomeLabel.setBorder(wlcmBorder);
        
        //creating the enter button and setting its properties
        enterButton = new JButton("Enter");
        
        enterButton.setPreferredSize(new Dimension(100,30));
        enterButton.setBorder(new LineBorder(Color.PINK, 2));
        
        //labels and their properties
        oldUserLabel = new JLabel("Old Username: ");
        newUserLabel = new JLabel("New Username: ");
        passUserLabel = new JLabel("Password: ");
        oldUserText = new JTextField(25);
        newUserText = new JTextField(25);
        passUserText = new JTextField(25);

        //adding the component to container
        con.add(welcomeLabel);
        con.add(oldUserLabel);
        con.add(oldUserText);
        con.add(newUserLabel);
        con.add(newUserText);
        con.add(passUserLabel);
        con.add(passUserText);
        con.add(enterButton);
        //getting the window toolkit to set the size and position of the container accordingly
        f2.setBounds(10, 10, 300, 450);  // Position and Size

        
        welcomeLabel.setFont(font1);
        welcomeLabel.setForeground(new Color(0,0,139));

        //setting some other basic properties on the container
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setVisible(true);
        f2.setResizable(false);
        con.setBackground(new Color(230,230,250));
        
        enterButton.addActionListener(this);
    }
    
    public void changePassPanel() {
        
        Container con = f3.getContentPane();
        con.removeAll();
        con.setLayout(new FlowLayout());

        //creating text fields and formatted text fields
        welcomeLabel = new JLabel("*****Change Password*****");
        welcomeLabel.setBorder(wlcmBorder);
        //creating the enter button and setting its properties
        enterButton1 = new JButton("Enter");

        //enter button and properties
        enterButton1.setPreferredSize(new Dimension(100,30));
        enterButton1.setBorder(new LineBorder(Color.PINK, 2));
        
        //label, texts and properties
        userPassLabel = new JLabel("Username: ");
        oldPassLabel = new JLabel("Old Password: ");
        newPassLabel = new JLabel("New Password: ");
        userPassText = new JTextField(25);
        oldPassText = new JTextField(25);
        newPassText = new JTextField(25);

        //adding the component to container
        con.add(welcomeLabel);
        con.add(userPassLabel);
        con.add(userPassText);
        con.add(oldPassLabel);
        con.add(oldPassText);
        con.add(newPassLabel);
        con.add(newPassText);
        con.add(enterButton1);
        
        //getting the window toolkit to set the size and position of the container accordingly
        f3.setBounds(10, 10, 300, 450);  // Position and Size

        
        welcomeLabel.setFont(font1);
        welcomeLabel.setForeground(new Color(0,0,139));

        //setting some other basic properties on the container
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.setVisible(true);
        f3.setResizable(false);
        con.setBackground(new Color(230,230,250));
        
        enterButton1.addActionListener(this);
    }
    
    public void registerPanel() {
        
        Container con = f4.getContentPane();
        con.removeAll();
        con.setLayout(new FlowLayout());

        //creating text fields and formatted text fields
        welcomeLabel = new JLabel("**********SIGN UP**********");
        welcomeLabel.setBorder(signUpBorder);
        
        //creating the enter button and setting its properties
        enterButton2 = new JButton("Register");
        enterButton2.setPreferredSize(new Dimension(100,30));
        enterButton2.setBorder(new LineBorder(new Color(0,128,0), 3));
        
        //labels and text properties
        userRLabel = new JLabel("Username: ");
        passRLabel = new JLabel("Password: ");
        userRText = new JTextField(25);
        passRText = new JTextField(25);

        //adding the component to container
        con.add(welcomeLabel);
        con.add(userRLabel);
        con.add(userRText);
        con.add(passRLabel);
        con.add(passRText);
        con.add(enterButton2);
        
        //getting the window toolkit to set the size and position of the container accordingly
        f4.setBounds(10, 10, 300, 450);  // Position and Size
        
        //label and texts properties
        welcomeLabel.setFont(font1);
        welcomeLabel.setForeground(new Color(0,100,0));

        //setting some other basic properties on the container
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.setVisible(true);
        f4.setResizable(false);
        con.setBackground(new Color(185,218,135));
        
        enterButton2.addActionListener(this);
    }

    public void initMethodLogin(){
        addFlag = true;
        showFullPanel();
    }
    
    //function that sends credentials to server
    public boolean sendUserPass() throws IOException, ClassNotFoundException {
       
        username = userText.getText();
        password = passText.getText();
        System.out.println(username + " " + password);
        User u = new User(username, password);
        boolean flag;
        
        try {
            oos.writeObject(u);
            flag = (boolean)ois.readObject();
            System.out.println("flag:  "+flag);
        } catch (IOException e) {
            throw new IOException("Exception in Login");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Exception in Login");
        }
        return flag;
    }
    
    public boolean sendRegisterUser() throws IOException, ClassNotFoundException {
        
        registerUser = userRText.getText();
        registerPassword = passRText.getText();
        System.out.println(registerUser + " " + registerPassword);
        User u = new User(registerUser, registerPassword);
        boolean flag;
        try {
            oos.writeObject(u);
            oos.flush();
            flag = (boolean)ois.readObject();
        } catch (IOException e) {
            throw new IOException("exception in writing or flush");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("exception in writing or flush");
        }
        //System.out.println("flag: " + flag);
        return flag;
    }

    //Action Listeners of all the buttons implemented here
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== loginButton){
            //System.out.println("in login action");
            try {
                    //1 for login
                output.write(1);
                loginFlag = sendUserPass();
                System.out.println("here");

            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            if (loginFlag) {
                //System.out.println("here");

                f.setVisible(false);
                initMethodLogin();
                //loggedInUser = new User(username,password);
            }
            userText.setText("");
            passText.setText("");
            loginFlag = false;

        }
        else if(e.getSource()== logoutButton){
            f1.setVisible(false);
            f.setVisible(true);
            username=null;
            password=null;
            loggedInUser=null;
            //3 for change username
            try {
                output.write(3);
                System.out.println("3 sent");

                //this.initMethod();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        else if(e.getSource()== changePass){
            f1.setVisible(false);
            changePassPanel();
            //f.setVisible(true);
            //2 for change username
            try {
                output.write(2);
                System.out.println("2 sent");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        else if(e.getSource()== changeUser){
            f1.setVisible(false);
            changeUsernamePanel();
            //1 for change username
            try {
                output.write(1);
                System.out.println("1 sent");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //f.setVisible(true);

        }
        else if(e.getSource()== enterButton){
            if(oldUserText.getText().equals(username)&&passUserText.getText().equals(password)&&newUserText.getText().length()<20) {
                           
                f2.setVisible(false);
                loggedInUser = new User(newUserText.getText(),password);
                System.out.println("usrnam: " + loggedInUser.GetUsername()+"  pass: " + loggedInUser.GetPassword());
                User tempUser = new User(loggedInUser);
                try {
                    oos.writeObject(loggedInUser);
                    oos.flush();
                    loggedInUser = (User)ois.readObject();
                    System.out.println("usrnam: " + loggedInUser.GetUsername()+"  pass: " + loggedInUser.GetPassword());
                    if (tempUser.GetUsername().equals(loggedInUser.GetUsername())){
                        System.out.println("in loooop");
                        username = loggedInUser.GetUsername();
                        password = loggedInUser.GetPassword();
                        Helper.DialogBox("Username Changed!");
                        f1.setVisible(true);
                    }
                    else {
                        Helper.DialogBox("Username already exists!");
                        f2.setVisible(true);
                    }
                    
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
             
                //changeUsernamePanel();
                
            }
            else{
                if (!oldUserText.getText().equals(username)){
                    Helper.DialogBox("Wrong Username");
                }
                else if (!passUserText.getText().equals(password)){
                    Helper.DialogBox("Wrong Password");
                }
                else if (newUserText.getText().length() >=20){
                    Helper.DialogBox("Username should be less than 20 characters");
                }
                
            }

        }
        else if(e.getSource()== enterButton1){
            if(userPassText.getText().equals(username)&&oldPassText.getText().equals(password)&&newPassText.getText().length()>=4&&newPassText.getText().length()<=10 ){
                f3.setVisible(false);
                loggedInUser = new User(username,newPassText.getText());
                try {
                    oos.writeObject(loggedInUser);
                    oos.flush();
                    loggedInUser = (User)ois.readObject();
                    username = loggedInUser.GetUsername();
                    password = loggedInUser.GetPassword();
                    Helper.DialogBox("Password Changed!");
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
                //changeUsernamePanel();
                f1.setVisible(true);
            }
            else{
                if (!userPassText.getText().equals(username)){
                    Helper.DialogBox("Wrong Username");
                }
                else if (!oldPassText.getText().equals(password)){
                    Helper.DialogBox("Wrong Password");
                }
                else if (newPassText.getText().length()<=4||newPassText.getText().length()>=10){
                    Helper.DialogBox("Password should be 4 to 10 characters long!");
                }
            }

        }
        else if(e.getSource()== registerButton){
            try {
                output.write(2);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //System.out.println("here in r button action");
            f.setVisible(false);
            registerPanel();
            f4.setVisible(true);

        }
        else if(e.getSource()== enterButton2){
            if(userRText.getText().length()<20&&passRText.getText().length()<=10&&passRText.getText().length()>=4){
                try {
                    //f4.setVisible(false);
                    registerUser = userRText.getText();
                    registerPassword = passRText.getText();
                    System.out.println(registerUser + " " + registerPassword);
                    User u = new User(registerUser, registerPassword);
                    boolean flag;
                    
                    oos.writeObject(u);
                    oos.flush();
                    flag = (boolean)ois.readObject();
                    
                    if (flag) {
                        
                        Helper.DialogBox("User Created Successfully!");
                        
                    }
                    else {
                        Helper.DialogBox("Username already exists!");
                        
                    }
                    flag = false;
                    f4.setVisible(false);
                    f.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                f4.setVisible(true);
                if (userRText.getText().length()>20){
                    Helper.DialogBox("Username should be less than 20 characters");
                }
                else if (passRText.getText().length()< 4 || passRText.getText().length()>10){
                    Helper.DialogBox("Password should be between 4 and 10 characters long");
                }
            }
        }
        
    }
    

}
