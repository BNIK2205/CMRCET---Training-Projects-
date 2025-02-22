    import java.util.ArrayList;
    import java.util.Scanner;

    class Dish {
        private String name;
        private double price;

        public Dish(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    class Drink extends Dish {
        private String size;

        public Drink(String name, double price, String size) {
            super(name, price);
            this.size = size;
        }

        @Override
        public double getPrice() {
            switch (size.toLowerCase()) {
                case "medium":
                    return super.getPrice() * 1.5;
                case "large":
                    return super.getPrice() * 2;
                default:
                    return super.getPrice();
            }
        }
    }
    interface BillingStrategy {
        double calculate(ArrayList<Dish> items);
    }

    class NormalBilling implements BillingStrategy {
        @Override
        public double calculate(ArrayList<Dish> items) {
            double total = 0;
            for (Dish item : items) {
                total += item.getPrice();
            }
            return total;
        }
    }

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

    class Menu {
        private ArrayList<Dish> dishes;
        private ArrayList<Drink> drinks;

        public Menu() {
            this.dishes = new ArrayList<>();
            this.drinks = new ArrayList<>();
        }

        public void addDish(Dish dish) {
            dishes.add(dish);
        }

        public void addDrink(Drink drink) {
            drinks.add(drink);
        }

        public void showMenu() {
            System.out.println("Menu:");
            int index = 1;
            for (Dish dish : dishes) {
                System.out.println(index++ + ". " + dish.getName() + " - $" + dish.getPrice());
            }
            for (Drink drink : drinks) {
                System.out.println(index++ + ". " + drink.getName() + " (" + drink.getPrice() + ")");
            }
        }

        public ArrayList<Dish> getItems() {
            ArrayList<Dish> items = new ArrayList<>(dishes);
            items.addAll(drinks);
            return items;
        }
    }


    class Order implements Runnable {

        private ArrayList<Dish> items;
        private String customerName;
        private BillingStrategy billingStrategy;

        public Order(String customerName, BillingStrategy billingStrategy) {
            this.items = new ArrayList<>();
            this.customerName = customerName;
            this.billingStrategy = billingStrategy;
        }

        public void addItem(Dish item) {
            items.add(item);
        }

        public double getOrderTotal() {
            return billingStrategy.calculate(items);
        }

        @Override
        public void run() {
            System.out.println("Processing order for " + customerName + "...");
            try {
                Thread.sleep(2000); // Simulate order processing time
            } catch (InterruptedException e) {
                System.out.println("Order processing interrupted.");
            }
            System.out.println("Order for " + customerName + " is ready!");
        }

    }

    public class Main {
        public static void main(String[] args) {

            Restaurant restaurant = new Restaurant("Gourmet Paradise");

            // Create some dishes and drinks
            Dish pizza = new Dish("Pizza", 12.00);
            Drink coke = new Drink("Coca Cola", 3.00, "large");
            Dish pasta = new Dish("Pasta", 10.00);

            restaurant.getMenu().addDish(pizza);
            restaurant.getMenu().addDrink(coke);
            restaurant.getMenu().addDish(pasta);

            Scanner scanner = new Scanner(System.in);

            restaurant.showMenu();

            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            int billingChoice = 0;
            while (true) {
                try {
                    System.out.println("Choose a billing strategy:");
                    System.out.println("1. Normal Billing");
                    billingChoice = Integer.parseInt(scanner.nextLine());
                    if (billingChoice == 1) {
                        break;
                    } else {
                        System.out.println("Invalid choice, please enter 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }


            System.out.println("Choose a billing strategy:");
            System.out.println("1. Normal Billing");

            BillingStrategy strategy = new NormalBilling();

            restaurant.createOrder(customerName, strategy);

            System.out.println("Total revenue of the restaurant: $" + restaurant.getTotalRevenue());
        }
    }