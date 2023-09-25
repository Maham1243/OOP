import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class AdminGui {
        Admin a1 = new Admin();
        Scanner input = new Scanner(System.in);

        public AdminGui(){
                a1.loadRestaurantsFromFile();

                JFrame frame = new JFrame();
                frame.getContentPane().setBackground(Color.WHITE);
                frame.setLocation(200,50);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1170, 650);
                frame.setLayout(null);

                Font font = new Font("Calibri", Font.BOLD, 40);

                JLabel label = new JLabel("Welcome to Admin Panel!");
                label.setBounds(250,50,1200,60);
                label.setFont(new Font("Consolas", Font.BOLD, 50));
                frame.add(label);

                JButton addRest = new JButton("Add Restaurant");
                JButton delRest = new JButton("Delete Restaurant");
                JButton viewRests = new JButton("View Restaurants");
                JButton frontPage = new JButton("Main Menu");

                addRest.setBackground(Color.BLACK);
                delRest.setBackground(Color.BLACK);
                viewRests.setBackground(Color.BLACK);
                frontPage.setBackground(Color.BLACK);

                addRest.setForeground(Color.WHITE);
                delRest.setForeground(Color.WHITE);
                viewRests.setForeground(Color.WHITE);
                frontPage.setForeground(Color.WHITE);

                addRest.setFont(font);
                delRest.setFont(font);
                viewRests.setFont(font);
                frontPage.setFont(font);

                addRest.setBounds(125,200,400,100);
                delRest.setBounds(650,200,400,100);
                viewRests.setBounds(125,400,400,100);
                frontPage.setBounds(650,400,400,100);

                frame.add(addRest);
                frame.add(delRest);
                frame.add(viewRests);
                frame.add(frontPage);

                addRest.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                System.out.println("Enter the name of the restaurant: ");
                                String name = input.nextLine();
                                a1.addRestaurant(name);
                                a1.saveRestaurantsToFile();
                                new AdminGui();
                        }
                });

                delRest.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                System.out.println("Enter the id of the restaurant you want to delete: ");
                                int id = input.nextInt();
                                a1.deleteRestaurant(id);
                                a1.saveRestaurantsToFile();
                                new AdminGui();
                        }
                });

                viewRests.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                a1.viewRestaurants();
                                System.out.println("Press 1 to go back to admin menu");
                                int choice = input.nextInt();
                                switch (choice){
                                        case 1: new AdminGui();
                                        break;
                                        default:
                                                System.out.println("Invalid choice. Please try again");
                                }
                        }
                });

                frontPage.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                new FrontPage();
                        }
                });

                frame.setVisible(true);
                frame.setResizable(false);
        }
}
