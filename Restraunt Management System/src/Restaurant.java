import java.util.ArrayList;
import java.util.Scanner;

class Restaurant {
    private String name;
    private Menu menu;
    private ArrayList<Order> orders;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new Menu();
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Order order : orders) {
            total += order.getOrderTotal();
        }
        return total;
    }

    public Menu getMenu() {
        return menu;
    }

    public void showMenu() {
        menu.showMenu();
    }

    public void createOrder(String customerName, BillingStrategy strategy) {
        Order order = new Order(customerName, strategy);
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            try {
                System.out.println("Enter the item number to add to your order or 'done' to finish:");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("done")) {
                    break;
                }

                int itemNumber = Integer.parseInt(choice) - 1;
                if (itemNumber >= 0 && itemNumber < menu.getItems().size()) {
                    order.addItem(menu.getItems().get(itemNumber));
                    System.out.println(menu.getItems().get(itemNumber).getName() + " added to your order.");
                } else {
                    System.out.println("Invalid item number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Item number out of range! Please try again.");
            }
        } while (true);


        addOrder(order);
        System.out.println("Order placed successfully!");
        System.out.println("Total cost: $" + order.getOrderTotal());
    }
}
