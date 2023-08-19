package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;

    // Constructor to create the MeterInfo frame
    MeterInfo(String meternumber) {
        this.meternumber = meternumber;

        setSize(700, 500);
        setLocation(400, 200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        // Label and field for Meter Number
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        p.add(lblmeternumber);

        // Label and choice for Meter Location
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 200, 20);
        p.add(meterlocation);

        // Label and choice for Meter Type
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 200, 20);
        p.add(metertype);

        // Label and choice for Phase Code
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        phasecode = new Choice();
        phasecode.add("011");
        // Add more phase codes as needed
        phasecode.setBounds(240, 200, 200, 20);
        p.add(phasecode);

        // Label and choice for Bill Type
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        // Add more bill types as needed
        billtype.setBounds(240, 240, 200, 20);
        p.add(billtype);

        // Label for Days
        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        p.add(lblemail);

        // Label for displaying the default number of days
        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(240, 280, 100, 20);
        p.add(lblemails);

        // Label for Note
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        p.add(lblphone);

        // Label for displaying the note
        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setBounds(240, 320, 500, 20);
        p.add(lblphones);

        // Button for submitting the meter information
        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        // Setting layout using BorderLayout
        setLayout(new BorderLayout());

        // Adding the panel to the center
        add(p, "Center");

        // Adding an image to the left side
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        // Setting background color of the frame
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    // ActionListener implementation for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Extracting user inputs from the fields and choices
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30"; // Default value

            // Creating SQL query for inserting meter information
            String query = "insert into meter_info values('" + meter + "', '" + location + "', '" + type + "', '" + code
                    + "', '" + typebill + "', '" + days + "')";

            try {
                // Connecting to the database and executing the query
                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    // Main method to run the MeterInfo class independently
    public static void main(String[] args) {
        new MeterInfo("");
    }
}

/*
 * This class represents the meter information page of your electricity billing
 * system application. It provides fields and choices for the user to enter
 * various meter details, such as meter location, meter type, phase code, and
 * bill type. The user can also see default values for days and a note. The
 * "Submit" button allows the user to save the meter information to the
 * database.
 */