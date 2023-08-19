package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// The Login class extends JFrame and implements the ActionListener interface
public class Login extends JFrame implements ActionListener {

    JButton login, cancel, signup;
    JTextField username, password;
    Choice logginin;
    
    // Constructor to create the Login frame
    Login() {
        super("Login Page"); // Set the title of the frame
        getContentPane().setBackground(Color.WHITE); // Set the background color
        setLayout(null); // Use absolute layout
        
        // Label and text field for Username
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);
        
        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);
        
        // Label and text field for Password
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400, 60, 150, 20);
        add(password);
        
        // Label and choice for User type (Admin or Customer)
        JLabel loggininas = new JLabel("Loggin in as");
        loggininas.setBounds(300, 100, 100, 20);
        add(loggininas);
        
        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400, 100, 150, 20);
        add(logginin);
        
        // Button for login with an icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);
        
        // Button for cancel with an icon
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(i4));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        // Button for signup with an icon
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);
        
        // Display an image on the left side
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);
        
        setSize(640, 300); // Set the frame size
        setLocation(400, 200); // Set the frame location
        setVisible(true); // Make the frame visible
    }
    
    // ActionListener implementation to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username.getText(); // Get the username
            String spassword = password.getText(); // Get the password
            String user = logginin.getSelectedItem(); // Get the user type (Admin or Customer)
            
            try {
                Conn c = new Conn(); // Create a connection to the database
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"'";
                
                ResultSet rs = c.s.executeQuery(query); // Execute the query
                
                if (rs.next()) {
                    String meter = rs.getString("meter_no"); // Get the meter number
                    setVisible(false); // Hide the login frame
                    new Project(user, meter); // Open the main Project frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login"); // Show an error message
                    username.setText(""); // Clear the username field
                    password.setText(""); // Clear the password field
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false); // Hide the login frame
        } else if (ae.getSource() == signup) {
            setVisible(false); // Hide the login frame
            
            new Signup(); // Open the signup frame
        }
    }
    
    // Main method to run the Login class independently
    public static void main(String[] args) {
        new Login();
    }
}

/*
 * This class represents the login page of your electricity billing system
 * application. It provides fields for the user to enter their username and
 * password, choose whether they are an admin or customer, and has buttons for
 * logging in, canceling, and signing up. It also displays an image on the left
 * side of the frame.
 */