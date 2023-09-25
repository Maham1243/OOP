import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {

    Admin a = new Admin();

    public void displayRestaurantNames() {
        a.loadRestaurantsFromFile();
        System.out.println("Available Restaurants:");
        for (Restaurant restaurant : a.restaurants) {
            System.out.println("- " + restaurant.getName());
        }
    }

    public Restaurant selectRestaurant() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the restaurant: ");
        String restaurantName = scanner.nextLine();
        for (Restaurant restaurant : a.restaurants) {
            if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
                return restaurant;
            }
        }
        System.out.println("Restaurant not found. Please try again.");
        return selectRestaurant();
    }

    public void displayMenu(Restaurant restaurant) {
        restaurant.viewMenu(restaurant);
    }

    public ArrayList<Restaurant.MenuItem> selectItems(Restaurant restaurant) {
        ArrayList<Restaurant.MenuItem> orderItems = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name of the item to add to your order (or 'done' to finish): ");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            boolean itemFound = false;
            for (Restaurant.MenuItem item : restaurant.getMenu()) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    orderItems.add(item);
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("Item not found in the menu. Please try again.");
            }
        }

        return orderItems;
    }

    public void placeOrder(ArrayList<Restaurant.MenuItem> orderItems) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter your address: ");
        String customerAddress = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String customerPhoneNumber = scanner.nextLine();

        double totalAmount = 0.0;
        System.out.println("\nOrder Details:");
        System.out.println("--------------");
        for (Restaurant.MenuItem item : orderItems) {
            System.out.println(item.getName() + " - $" + item.getPrice());
            totalAmount += item.getPrice();
        }
        System.out.println("--------------");
        System.out.println("Total Amount: $" + totalAmount);

        System.out.println("\nThank you, " + customerName + "! Your order has been placed.");
        System.out.println("Delivery Address: " + customerAddress);
        System.out.println("Phone Number: " + customerPhoneNumber);
    }
}
