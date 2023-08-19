package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {

    // Choice component to select the month
    Choice cmonth;

    // Buttons for payment and going back
    JButton pay, back;

    // Variable to store the meter number
    String meter;

    // Constructor to initialize the GUI
    PayBill(String meter) {
        this.meter = meter;

        // Set layout to null (not recommended)
        setLayout(null);

        // Set window dimensions and position
        setBounds(300, 150, 900, 600);

        // Heading label
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        // Meter Number label
        JLabel lblMeterNumber = new JLabel("Meter Number");
        lblMeterNumber.setBounds(35, 80, 200, 20);
        add(lblMeterNumber);

        // Label to display meter number
        JLabel labelMeterNumber = new JLabel("");
        labelMeterNumber.setBounds(300, 80, 200, 20);
        add(labelMeterNumber);

        // Name label
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(35, 140, 200, 20);
        add(lblName);

        // Label to display name
        JLabel labelName = new JLabel("");
        labelName.setBounds(300, 140, 200, 20);
        add(labelName);

        // Month label
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(35, 200, 200, 20);
        add(lblMonth);

        // Choice component to select month
        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
        // Adding months to the choice
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
        add(cmonth);

        // Units label
        JLabel lblUnits = new JLabel("Units");
        lblUnits.setBounds(35, 260, 200, 20);
        add(lblUnits);

        // Label to display units
        JLabel labelUnits = new JLabel("");
        labelUnits.setBounds(300, 260, 200, 20);
        add(labelUnits);

        // Total Bill label
        JLabel lblTotalBill = new JLabel("Total Bill");
        lblTotalBill.setBounds(35, 320, 200, 20);
        add(lblTotalBill);

        // Label to display total bill
        JLabel labelTotalBill = new JLabel("");
        labelTotalBill.setBounds(300, 320, 200, 20);
        add(labelTotalBill);

        // Status label
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(35, 380, 200, 20);
        add(lblStatus);

        // Label to display status
        JLabel labelStatus = new JLabel("");
        labelStatus.setBounds(300, 380, 200, 20);
        labelStatus.setForeground(Color.RED);
        add(labelStatus);

        // Try to fetch data from the database and update labels
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                labelMeterNumber.setText(meter);
                labelName.setText(rs.getString("name"));
            }

            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = 'January'");
            while (rs.next()) {
                labelUnits.setText(rs.getString("units"));
                labelTotalBill.setText(rs.getString("totalbill"));
                labelStatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Item listener for the month choice
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '"
                            + cmonth.getSelectedItem() + "'");
                    while (rs.next()) {
                        labelUnits.setText(rs.getString("units"));
                        labelTotalBill.setText(rs.getString("totalbill"));
                        labelStatus.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Pay button
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);

        // Back button
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);

        // Set the background color of the content pane
        getContentPane().setBackground(Color.WHITE);

        // Load and display an image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image image = icon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(400, 120, 600, 300);
        add(imageLabel);

        // Make the frame visible
        setVisible(true);
    }

    // ActionListener implementation
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '" + meter + "' AND month='"
                        + cmonth.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else {
            setVisible(false);
        }
    }

    // Main method
    public static void main(String[] args) {
        new PayBill("");
    }
}

/*
 * This class represents the bill payment page of your electricity billing
 * system application. It displays bill details and allows users to pay their
 * bills. The class includes various components such as labels, choice menus,
 * buttons, and an image display. It retrieves data from the database and
 * updates labels accordingly. When the user clicks the "Pay" button, it updates
 * the bill status in the database and opens the Paytm frame for payment
 * processing.
 */