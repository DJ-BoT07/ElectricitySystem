package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // Used for converting ResultSet to JTable data
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {

    JTable table;
    JButton print;

    CustomerDetails() {
        super("Customer Details");

        setSize(1200, 650); // Set the frame size
        setLocation(200, 150); // Set the frame location

        table = new JTable(); // Create a table

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");

            // Use DbUtils to populate the table with data from the database
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table); // Create a scrollable pane for the table
        add(sp);

        print = new JButton("Print"); // Create a print button
        print.addActionListener(this); // Register ActionListener
        add(print, "South"); // Add the button to the bottom of the frame

        setVisible(true); // Make the frame visible
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            table.print(); // Print the table
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails(); // Create an instance of CustomerDetails
    }
}

/*
 * This CustomerDetails class is a Java Swing application that displays customer
 * details related to electricity billing. The application provides a user
 * interface where users can view customer details stored in the database. The
 * details are displayed in a JTable. Users can also print the displayed table
 * using the "Print" button. The application interacts with the database using
 * the Conn class to retrieve data.
 */