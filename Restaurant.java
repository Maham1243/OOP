import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Restaurant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    public ArrayList<MenuItem> menu;

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
        this.menu = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<MenuItem> getMenu() {
        return menu;
    }
    public boolean login(int restaurantId) {
        return this.id == restaurantId;
    }

    public static Restaurant login(ArrayList<Restaurant> restaurants, int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.login(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    public void loadMenuFromFile(Restaurant r) {
        String filename = r.getName() + "_menu.txt";
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Read the list of menu items from the file
            menu = (ArrayList<MenuItem>) ois.readObject();
//            System.out.println("Menu loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading menu from file: " + e.getMessage());
        }
    }

    public void saveMenuToFile(Restaurant r) {
        String filename = r.getName() + "_menu.txt";
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // Write the list of menu items to the file
            oos.writeObject(menu);
//            System.out.println("Menu saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving menu to file: " + e.getMessage());
        }
    }



    public void addItemToMenu(String itemName, double price, Restaurant r) {
        this.loadMenuFromFile(r);
        MenuItem item = new MenuItem(itemName, price);
        menu.add(item);
        this.saveMenuToFile(r);
    }

    public void removeItemFromMenu(String itemName) {
        MenuItem itemToRemove = null;
        for (MenuItem item : menu) {
            if (item.getName().equals(itemName)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            menu.remove(itemToRemove);
        } else {
            System.out.println("Item not found in the menu. Please enter a valid item name.");
        }
    }
    public void viewMenu(Restaurant r) {
        loadMenuFromFile(r);
        System.out.println("Menu for " + this.name);
        System.out.println("-----------------------");
        for (MenuItem item : menu) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }


    public void updateItemPrice(String itemName, double newPrice) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setPrice(newPrice);
                System.out.println("Item price updated successfully.");
                return;
            }
        }
        System.out.println("Item not found in the menu.");
    }

    static class MenuItem implements Serializable {
        private String name;
        private double price;
        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

}
