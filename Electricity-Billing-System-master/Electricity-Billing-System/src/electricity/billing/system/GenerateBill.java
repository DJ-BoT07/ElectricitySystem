package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    // Constructor to create the GenerateBill frame
    GenerateBill(String meter) {
        this.meter = meter;

        setSize(500, 800); // Set the frame size
        setLocation(550, 30); // Set the frame location

        setLayout(new BorderLayout()); // Use BorderLayout

        JPanel panel = new JPanel(); // Create a panel

        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);

        cmonth = new Choice(); // Create a choice for selecting months

        // Add months to the choice
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");

        area = new JTextArea(50, 15);
        area.setText(
                "\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));

        JScrollPane pane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        // Add components to the panel
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel, BorderLayout.NORTH);

        add(pane, BorderLayout.CENTER);
        add(bill, BorderLayout.SOUTH);

        setVisible(true); // Make the frame visible
    }

    // ActionListener implementation to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();

            String month = cmonth.getSelectedItem(); // Get the selected month

            // Set initial text for the bill area
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF " + month
                    + ", 2022\n\n\n");

            // Retrieve customer information from the database
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");

            if (rs.next()) {
                // Append customer information to the bill area
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address             : " + rs.getString("address"));
                area.append("\n    City                 : " + rs.getString("city"));
                area.append("\n    State                : " + rs.getString("state"));
                area.append("\n    Email                : " + rs.getString("email"));
                area.append("\n    Phone                 : " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            // Retrieve meter information from the database
            rs = c.s.executeQuery("select * from meter_info where meter_no = '" + meter + "'");

            if (rs.next()) {
                // Append meter information to the bill area
                area.append("\n    Meter Location: " + rs.getString("meter_location"));
                area.append("\n    Meter Type:     " + rs.getString("meter_type"));
                area.append("\n    Phase Code:        " + rs.getString("phase_code"));
                area.append("\n    Bill Type:          " + rs.getString("bill_type"));
                area.append("\n    Days:                " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            // Retrieve tax information from the database
            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                // Append tax information to the bill area
                area.append("\n");
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent:     " + rs.getString("meter_rent"));
                area.append("\n    Service Charge:        " + rs.getString("service_charge"));
                area.append("\n    Service Tax:          " + rs.getString("service_tax"));
                area.append("\n    Swacch Bharat Cess:                " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: " + rs.getString("fixed_tax"));
                area.append("\n");
            }

            // Retrieve bill information from the database
            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' and month='" + month + "'");

            if (rs.next()) {
                // Append bill information to the bill area
                area.append("\n");
                area.append("\n    Current Month: " + rs.getString("month"));
                area.append("\n    Units Consumed:     " + rs.getString("units"));
                area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to run the GenerateBill class independently
    public static void main(String[] args) {
        new GenerateBill("");
    }
}

/*
 * The GenerateBill class creates a frame that allows the user to generate an
 * electricity bill for a selected month. The user can choose a month using a
 * Choice component and click the "Generate Bill" button to retrieve and display
 * the bill details for the selected month. The class interacts with the
 * database to retrieve customer, meter, tax, and bill information based on the
 * meter number and selected month. The information is then displayed in a
 * JTextArea.
 */