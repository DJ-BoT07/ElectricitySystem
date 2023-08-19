package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    // Constructor for the BillDetails class
    BillDetails(String meter) {
        // Set the size and location of the frame
        setSize(700, 650);
        setLocation(400, 150);

        // Set the background color of the frame
        getContentPane().setBackground(Color.WHITE);

        // Create a JTable to display the bill details
        JTable table = new JTable();

        try {
            // Establish a database connection using the Conn class
            Conn c = new Conn();

            // Create a query to retrieve bill details for the given meter number
            String query = "select * from bill where meter_no = '" + meter + "'";

            // Execute the query and get the result set
            ResultSet rs = c.s.executeQuery(query);

            // Set the result set as the data source for the JTable using DbUtils
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a JScrollPane to accommodate the JTable
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        add(sp);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the BillDetails class (replace "" with an actual meter
        // number)
        new BillDetails("");
    }
}

/*
 * This class creates a window that displays the bill details in a table format.
 * It takes a meter number as a parameter to fetch the corresponding bill
 * details from the database. The DbUtils.resultSetToTableModel(rs) method is
 * used to convert the ResultSet into a format that can be displayed in a
 * JTable.
 */