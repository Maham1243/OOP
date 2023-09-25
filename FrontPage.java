import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontPage extends JFrame {
    Admin a = new Admin();
    Scanner input = new Scanner(System.in);
    FrontPage(){
        JFrame frame = new JFrame("FOOD DELIVERY SERVICE");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocation(200,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1170, 650);
        frame.setLayout(null);

        Font font = new Font("Calibri", Font.BOLD, 40);

        // Create a label for the sample text
        JLabel label = new JLabel("Welcome to Online Food Delivery Service!");
        label.setBounds(150,30,1200,60);
        label.setFont(new Font("Consolas", Font.BOLD, 40));
        frame.add(label);

        // Create the buttons
        JButton adminButton = new JButton("Admin");
        JButton restaurantButton = new JButton("Restaurant");
        JButton customerButton = new JButton("Customer");

        adminButton.setBackground(Color.BLACK);
        restaurantButton.setBackground(Color.BLACK);
        customerButton.setBackground(Color.BLACK);

        adminButton.setForeground(Color.WHITE);
        restaurantButton.setForeground(Color.WHITE);
        customerButton.setForeground(Color.WHITE);

        adminButton.setFont(font);
        restaurantButton.setFont(font);
        customerButton.setFont(font);

        adminButton.setBounds(400,150,300,100);
        restaurantButton.setBounds(400,300,300,100);
        customerButton.setBounds(400,450,300,100);

        frame.add(adminButton);
        frame.add(restaurantButton);
        frame.add(customerButton);

        // Add action listeners to the buttons
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });

        restaurantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.loadRestaurantsFromFile();
                frame.setVisible(false);
                while (true) {
                    System.out.println("Enter ID: ");
                    int id = input.nextInt();
                    input.nextLine(); // Consume the newline character after reading the integer

                    boolean restaurantFound = false;
                    for (int i = 0 ; i<a.restaurants.size();i++) {
                        if (a.restaurants.get(i).getId() == id) {
                            new RestRun(a.restaurants.get(i));
                            restaurantFound = true;
                            break; // Exit the loop when a restaurant is found
                        }
                    }
                    if (restaurantFound) {
                        break; // Exit the while loop
                    } else {
                        System.out.println("Restaurant not found. Please try again.");
                    }
                }
            }
        });


        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Order o = new Order();
                System.out.println("Select Restaurant: ");
                o.displayRestaurantNames();
                Restaurant r = o.selectRestaurant();
                r.loadMenuFromFile(r);
                o.displayMenu(r);
                ArrayList<Restaurant.MenuItem> orderItems = o.selectItems(r);
                o.placeOrder(orderItems);
            }
        });

        // Display the frame
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
