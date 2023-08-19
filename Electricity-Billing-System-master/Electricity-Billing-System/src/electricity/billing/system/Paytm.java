package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton back;

    // Constructor for the Paytm class
    Paytm(String meter) {
        this.meter = meter;

        // Create a JEditorPane to display web content
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            // Set the content of the JEditorPane to a Paytm URL
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            // Handle the case where the URL cannot be loaded
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
        }

        // Create a JScrollPane to display the JEditorPane
        JScrollPane pane = new JScrollPane(j);
        add(pane);

        // Create a "Back" button
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        // Add the "Back" button to the JEditorPane
        j.add(back);

        // Set the size, location, and visibility of the frame
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    // ActionListener implementation for the "Back" button
    public void actionPerformed(ActionEvent ae) {
        // Hide the current frame and go back to the PayBill frame
        setVisible(false);
        new PayBill(meter);
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create an instance of Paytm with an empty meter value
        new Paytm("");
    }
}

/*
 * This class opens the Paytm payment portal within the application for users to
 * complete their bill payment. It provides a "Back" button to return to the
 * PayBill frame if the user decides to cancel or go back from the payment page.
 * The JEditorPane is used to display web content (in this case, the Paytm
 * portal) within the frame.
 */
