import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Admin extends User implements Serializable {

    public ArrayList<Restaurant> restaurants;
    static String[] admins = {"saram", "maham", "iqra"};
    static String[] passes = {"1234", "5678", "9852"};

    static Scanner input = new Scanner(System.in);

    public Admin() {

        restaurants = new ArrayList<>();
    }
    public void createMenuFile(String name) {
        String fileName = name + "_menu.txt";
        File menuFile = new File(fileName);

        try {
            if (menuFile.createNewFile()) {
                System.out.println("Menu file created successfully: " + fileName);
            } else {
                System.out.println("Menu file already exists: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Failed to create menu file: " + fileName);
            e.printStackTrace();
        }
    }

    public void deleteMenuFile(Restaurant restaurant) {
        String fileName = restaurant.getName() + "_menu.txt";
        File menuFile = new File(fileName);

        if (menuFile.exists()) {
            if (menuFile.delete()) {
                System.out.println("Menu file deleted successfully: " + fileName);
            } else {
                System.out.println("Failed to delete menu file: " + fileName);
            }
        } else {
            System.out.println("Menu file does not exist: " + fileName);
        }
    }

    public void addRestaurant(String name) {
        // Generate a random restaurant ID
        Random random = new Random();
        int id = random.nextInt(1000);

        // Create a new Restaurant object
        Restaurant restaurant = new Restaurant(id, name);
        createMenuFile(name);

        // Add the restaurant to the list
        restaurants.add(restaurant);
    }

    public void deleteRestaurant(int id) {
        // Find the restaurant with the given ID
        Restaurant restaurantToDelete = null;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                restaurantToDelete = restaurant;
                break;
            }
        }

        // Delete the restaurant from the list
        if (restaurantToDelete != null) {
            restaurants.remove(restaurantToDelete);
            deleteMenuFile(restaurantToDelete);
        }
    }

    public void viewRestaurants() {
        // Display information for each restaurant
        for (Restaurant restaurant : restaurants) {
            System.out.println("ID: " + restaurant.getId());
            System.out.println("Name: " + restaurant.getName());
            System.out.println("--------------------------");
        }
    }

    public void saveRestaurantsToFile() {
        try (FileOutputStream fos = new FileOutputStream("restaurants.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // Write the list of restaurants to the file
            oos.writeObject(restaurants);
        } catch (IOException e) {
            System.out.println("Error saving restaurants to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadRestaurantsFromFile() {
        try (FileInputStream fis = new FileInputStream("restaurants.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Read the list of restaurants from the file
            restaurants = (ArrayList<Restaurant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading restaurants from file: " + e.getMessage());
        }
    }
}
