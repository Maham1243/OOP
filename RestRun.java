import java.util.Scanner;

public class RestRun {
    Scanner input = new Scanner(System.in);

    public RestRun(Restaurant r) {
        System.out.println(r.getName() + " panel");


        r.loadMenuFromFile(r);

        System.out.println("Press 1 to view menu");
        System.out.println("Press 2 to add item");
        System.out.println("Press 3 to delete item");
        System.out.println("Press 4 to update price");
        System.out.println("Press 5 to return to main menu");

        int choice = input.nextInt();

        switch (choice){
            case 1 :
                r.loadMenuFromFile(r);
                r.viewMenu(r);
                new RestRun(r);
                break;
            case 2 :
                System.out.println("Enter item name: ");
                String name = input.next();
                input.nextLine();
                System.out.println("Enter item price: ");
                double price = input.nextDouble();
                r.addItemToMenu(name, price, r);
                new RestRun(r);
                break;
            case 3 :
                r.viewMenu(r);
                System.out.println("Enter the name of item you want to remove: ");
                input.nextLine();
                String delName = input.nextLine();
                r.removeItemFromMenu(delName);
                r.saveMenuToFile(r);
                new RestRun(r);
                break;
            case 4 :
                System.out.println("Enter item name: ");
                input.nextLine();
                String itemName = input.nextLine();
                System.out.println("Enter item price: ");
                double newPrice = input.nextDouble();
                r.updateItemPrice(itemName, newPrice);
                r.saveMenuToFile(r);
                new RestRun(r);
                break;
            case 5 :
                new FrontPage();
        }
    }
}
