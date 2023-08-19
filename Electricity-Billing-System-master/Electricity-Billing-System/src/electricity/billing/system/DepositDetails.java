package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // Used for converting ResultSet to JTable data
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    DepositDetails() {
        super("Deposit Details");

        setSize(700, 700); // Set the frame size
        setLocation(400, 100); // Set the frame location

        setLayout(null); // Use absolute layout
        getContentPane().setBackground(Color.WHITE); // Set background color

        // Create and add components

        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no")); // Populate the meter number choice with data from the
                                                           // database
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
        cmonth.add("January");
        cmonth.add("February");
        // ... Add other months
        add(cmonth);

        table = new JTable(); // Create a table

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");

            // Use DbUtils to populate the table with data from the database
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table); // Create a scrollable pane for the table
        sp.setBounds(0, 100, 700, 600); // Set the bounds of the pane
        add(sp);

        search = new JButton("Search"); // Create a search button
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this); // Register ActionListener
        add(search);

        print = new JButton("Print"); // Create a print button
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this); // Register ActionListener
        add(print);

        setVisible(true); // Make the frame visible
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) { // If the search button is clicked
            String query = "select * from bill where meter_no = '" + meternumber.getSelectedItem() + "' and month = '"
                    + cmonth.getSelectedItem() + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query); // Execute the query
                table.setModel(DbUtils.resultSetToTableModel(rs)); // Update the table with query results
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // If the print button is clicked
            try {
                table.print(); // Print the table
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails(); // Create an instance of DepositDetails
    }
}

/*
 * This DepositDetails class is a Java Swing application that displays deposit
 * details related to electricity bills. The application provides a user
 * interface where users can select a meter number and a month to search for
 * specific deposit details. The details are displayed in a JTable. Users can
 * also print the displayed table using the "Print" button. The application
 * interacts with the database using the Conn class to retrieve data.
 */