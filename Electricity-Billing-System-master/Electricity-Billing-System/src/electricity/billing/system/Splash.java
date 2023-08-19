package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t;

    // Constructor for the Splash class
    Splash() {
        // Create an ImageIcon for the splash image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // Create a JLabel to display the splash image
        JLabel image = new JLabel(i3);
        add(image);

        // Set the frame visibility
        setVisible(true);

        // Display animation for resizing the splash screen
        int x = 1;
        for (int i = 2; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x) / 2), 400 - (i / 2));
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Create and start a thread for the splash screen
        t = new Thread(this);
        t.start();

        // Set the frame visibility again (it was already set earlier)
        setVisible(true);
    }

    // Run method for the thread
    public void run() {
        try {
            // Sleep for a period (7000 milliseconds) before closing the splash screen
            Thread.sleep(7000);
            setVisible(false);

            // Open the login frame after the sleep period
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create a new instance of the Splash class
        new Splash();
    }
}

/*
 * Splash class provides an engaging and visually appealing splash screen that
 * introduces your electricity billing system application. It uses threading and
 * animations to manage the splash screen's lifecycle and transition smoothly to
 * the login screen after a set duration.
 */